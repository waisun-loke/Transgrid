/**
 *@Ventyx 2012
 *
 * This program is a data dump of fitted equipment on the msf600 file
 * which each item of fitted equipment retrieved will also show its 
 * relationship to the MSF600 fixed equipment record
 */
package com.mincom.ellipse.script.custom;

import java.text.SimpleDateFormat

import org.apache.commons.beanutils.BeanUtils
import org.slf4j.LoggerFactory

import com.mincom.batch.environment.BatchEnvironment
import com.mincom.batch.script.*
import com.mincom.ellipse.edoi.ejb.msf010.*
import com.mincom.ellipse.edoi.ejb.msf600.*
import com.mincom.ellipse.edoi.ejb.msf610.*
import com.mincom.ellipse.edoi.ejb.msf650.MSF650Rec
import com.mincom.ellipse.edoi.ejb.msf6a0.*
import com.mincom.ellipse.edoi.ejb.msf6a2.*
import com.mincom.ellipse.edoi.ejb.msf6a3.*
import com.mincom.ellipse.edoi.ejb.msf6a4.*
import com.mincom.ellipse.edoi.ejb.msfh67.MSFH67Key
import com.mincom.ellipse.edoi.ejb.msfh67.MSFH67Rec
import com.mincom.ellipse.edoi.ejb.msfx6b.*
import com.mincom.ellipse.script.util.*
import com.mincom.enterpriseservice.ellipse.ErrorMessageDTO
import com.mincom.enterpriseservice.ellipse.equipment.EquipmentServiceReadReplyDTO
import com.mincom.enterpriseservice.ellipse.equipment.EquipmentServiceReadRequestDTO
import com.mincom.enterpriseservice.ellipse.equipment.EquipmentServiceReadRequiredAttributesDTO
import com.mincom.enterpriseservice.ellipse.equipment.EquipmentServiceRetrieveReplyCollectionDTO
import com.mincom.enterpriseservice.ellipse.equipment.EquipmentServiceRetrieveReplyDTO
import com.mincom.enterpriseservice.ellipse.equipment.EquipmentServiceRetrieveRequestDTO
import com.mincom.enterpriseservice.ellipse.equipment.EquipmentServiceRetrieveRequiredAttributesDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceFetchCurrentFitmentsReplyDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceFetchCurrentFitmentsRequestDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceFetchCurrentFitmentsRequiredAttributesDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceRetrieveEquipProfilesReplyCollectionDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceRetrieveEquipProfilesReplyDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceRetrieveEquipProfilesRequestDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceRetrieveEquipProfilesRequiredAttributesDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceRetrieveFitEquipTracingReplyCollectionDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceRetrieveFitEquipTracingReplyDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceRetrieveFitEquipTracingRequestDTO
import com.mincom.enterpriseservice.ellipse.equiptrace.EquipTraceServiceRetrieveFitEquipTracingRequiredAttributesDTO
import com.mincom.enterpriseservice.ellipse.nameplate.NameplateServiceRetrieveReplyCollectionDTO
import com.mincom.enterpriseservice.ellipse.nameplate.NameplateServiceRetrieveReplyDTO
import com.mincom.enterpriseservice.ellipse.nameplate.NameplateServiceRetrieveRequestDTO
import com.mincom.enterpriseservice.ellipse.nameplate.NameplateServiceRetrieveRequiredAttributesDTO
import com.mincom.enterpriseservice.ellipse.table.*
import com.mincom.eql.*
import com.mincom.eql.impl.*

public class ParamsTrb601{
	//List of Input Parameters
	String paramDistrict;
	String paramProductiveUnit;
	String paramEquipStatus;
	String paramRegCategory;
	String paramCustomer;
	String paramTracingAction;
	String paramComponentCode;
	String paramEquipGroupId;
	String paramOriginatingDoc;
	String paramMnemonic;
}

public class ProcessTrb601 extends SuperBatch {

	/*
	 * private class
	 */

	private class TRT601Obj {
		String sInstallEquipRef = " "
		String sInstallEquipmentDesc = " "
		String sEquipStatus = " "
		String sRegCategoryDesc = " "
		String sCustomerDesc = " "
		String sFitEquipment = " "
		String sMnemonic = " "
		String sOriginalDoc = " "
		String sPartNo = " "
		String sEquipmentGrpId = " "
		String sCompCode = " "
		String sModCode = " "
		String sVnominal = " "
		String sIload = " "
		String sIfault = " "
		String sIfaultt = " "
		String sInsulation = " "
		String sType = " "
		String sInsulMass = " "
		String firstTrcDate = " "
		String lastTrcDate = " "
		String origRefNum = " "
		String origRefType = " "
		String sSerialNum = " "
		String lastAction = " "
		String lastActionDesc = " "
	}

	/*
	 * Constants
	 */
	private static final int MAX_ROW_READ = 1000
	private static final String SERVICE_EQUIPMENT  = "EQUIPMENT"
	private static final String SERVICE_EQUIPTRACE = "EQUIPTRACE"
	private static final String SERVICE_NAMEPLATE  = "NAMEPLATE"
	private static final String SERVICE_TABLE      = "TABLE"
	private static final String CSV_TRT601 = "TRT601"
	private static final String MIN_DATE = "19000101"
	private static final int MAX_INSTANCE = 20
	private static final String REPORT_NAME = "TRB601A"
	private static final ArrayList<String> DEFITMENT_STATUS = ["C", "I", "K", "H", "J"]
	/*
	 * variables
	 */
	private File trt601File;
	private BufferedWriter csvTrt601Writer
	private def     reportA
	private boolean bReportAOpen   = false;
	private boolean firstErr = true
	private TRT601Obj trt601CSVDetail
	private String fieldRef
	private String errValue
	private String errMsg
	private boolean firstCsv = true, browseFittedEq
	private MSF600Rec equipmentRec
	private ArrayList<String> fittedEquipmentList
	private HashMap<String, String> customerDescriptionMap, regulatoryCategoryMap


	/* 
	 * IMPORTANT!
	 * Update this Version number EVERY push to GIT 
	 */
	private def version = 9
	private ParamsTrb601 batchParams;

	public void runBatch(Binding b){

		init(b);

		printSuperBatchVersion();
		info("runBatch Version : " + version);

		batchParams = params.fill(new ParamsTrb601())

		//PrintRequest Parameters
		info("paramDistrict       : " + batchParams.paramDistrict)
		info("paramProductiveUnit : " + batchParams.paramProductiveUnit)
		info("paramEquipStatus    : " + batchParams.paramEquipStatus)
		info("paramRegCategory    : " + batchParams.paramRegCategory)
		info("paramCustomer       : " + batchParams.paramCustomer)
		info("paramTracingAction  : " + batchParams.paramTracingAction)
		info("paramComponentCode  : " + batchParams.paramComponentCode)
		info("paramEquipGroupId   : " + batchParams.paramEquipGroupId)
		info("paramOriginatingDoc : " + batchParams.paramOriginatingDoc)
		info("paramMnemonic       : " + batchParams.paramMnemonic)

		try {
			processBatch();
		}
		catch(Exception e){
			def logObject = LoggerFactory.getLogger(getClass());
			logObject.trace("------------- TRB601A ERROR TRACE ", e)
			info("Process terminated. ${e.getMessage()}")
		}
		finally {
			printBatchReport();
		}
	}

	/**
	 * Check if browsing Fitted Equipment needed.
	 * @return true if Fitted Equipment needed
	 */
	private boolean browseFittedEquipmentNeeded() {
		info("browseFittedEquipmentNeeded")
		boolean fittedEquipmentFilled = (batchParams.paramTracingAction?.trim()
				|| batchParams.paramComponentCode?.trim()
				|| batchParams.paramEquipGroupId?.trim()
				|| batchParams.paramOriginatingDoc?.trim()
				|| batchParams.paramMnemonic?.trim())
		boolean fixedEquipmentEmpty = (!batchParams.paramProductiveUnit?.trim()
				&& !batchParams.paramEquipStatus?.trim()
				&& !batchParams.paramRegCategory?.trim()
				&& !batchParams.paramCustomer?.trim())
		return fixedEquipmentEmpty && fittedEquipmentFilled
	}

	private void processBatch(){
		info("processBatch")
		//populate Customer and Regulatory Category
		customerDescriptionMap = new HashMap<String, String>()
		regulatoryCategoryMap = new HashMap<String, String>()
		populateCustomerMap()
		populateRegCategoryMap()
		browseFittedEq = browseFittedEquipmentNeeded()
		if(browseFittedEq) {
			processBrowseFittedEquipment()
		} else {
			//populate productive unit hierarchy if needed
			if(batchParams.paramProductiveUnit?.trim()) {
				fittedEquipmentList = new ArrayList<String>()
				EquipmentServiceRetrieveReplyDTO prodUnitDto =
						readProductiveUnit(batchParams.paramProductiveUnit.trim())
				if(prodUnitDto) {
					fittedEquipmentList.add(prodUnitDto?.getEquipmentNo().trim())
					fittedEquipmentList.addAll(browseProductiveUnitHierarchy(prodUnitDto?.getEquipmentNo()?.trim()))
					debug("productive unit hierarchy results: ${fittedEquipmentList}")
				}
			}
			processBrowseFixedEquipment()
		}
	}

	/**
	 * Retrieve equipment using edoi.
	 */
	private void processBrowseFixedEquipment() {
		info("processBrowseFixedEquipment")
		QueryImpl query = new QueryImpl(MSF600Rec.class)
				.and(MSF600Key.equipNo.greaterThanEqualTo(" "))
				.and(MSF600Rec.dstrctCode.equalTo(batchParams.paramDistrict))
				.and(MSF600Rec.traceableFlg.notEqualTo("Y")) //Fixed Equipment is not traceable.
				.orderBy(MSF600Rec.msf600Key)

		if(batchParams.paramEquipStatus?.trim()) {
			Constraint cEquipStatus = MSF600Rec.equipStatus.equalTo(batchParams.paramEquipStatus.trim())
			query = query.and(cEquipStatus)
		}
		if(batchParams.paramRegCategory?.trim()) {
			Constraint cEquipClassifx1 = MSF600Rec.equipClassifx1.equalTo(batchParams.paramRegCategory.trim())
			query = query.and(cEquipClassifx1)
		}
		if(batchParams.paramCustomer?.trim()) {
			Constraint cEquipClassifx2 = MSF600Rec.equipClassifx2.equalTo(batchParams.paramCustomer.trim())
			query = query.and(cEquipClassifx2)
		}

		int msf600Count = 0
		try {
			edoi.search(query, MAX_ROW_READ, { MSF600Rec msf600Rec->
				msf600Count++
				equipmentRec = msf600Rec
				String equipmentNo = msf600Rec.getPrimaryKey().getEquipNo().trim()
				boolean contProUnit = true

				if(batchParams.paramProductiveUnit?.trim() &&
				!fittedEquipmentList.contains(equipmentNo)) {
					contProUnit = false
				}

				if(contProUnit) {
					debug("fixed equipment : ${equipmentNo}")
					retrieveEquipProfiles(equipmentNo)
				}
			})
		} catch(Exception e) {
			info("processBrowseFixedEquipment()#searchMSF600 closure was aborted due to: ${e.getMessage()}")
		}
	}

	/**
	 * Browse Fitted Equipment
	 * @param fittedEquipNo
	 */
	private void processBrowseFittedEquipment() {
		info("processBrowseFittedEquipment")
		QueryImpl query = new QueryImpl(MSF600Rec.class)
				.and(MSF600Key.equipNo.greaterThanEqualTo(" "))
				.and(MSF600Rec.dstrctCode.equalTo(batchParams.paramDistrict))
				.and(MSF600Rec.traceableFlg.equalTo("Y")) //Fitted Equipment is traceable.
				.orderBy(MSF600Rec.msf600Key)

		if(batchParams.paramComponentCode?.trim()) {
			query = query.and(MSF600Rec.compCode.equalTo(batchParams.paramComponentCode.trim()))
		}
		if(batchParams.paramEquipGroupId?.trim()) {
			query = query.and(MSF600Rec.equipGrpId.equalTo(batchParams.paramEquipGroupId.trim()))
		}
		if(batchParams.paramOriginatingDoc?.trim()) {
			query = query.and(MSF600Rec.originalDoc.equalTo(batchParams.paramOriginatingDoc.trim()))
		}
		if(batchParams.paramMnemonic?.trim()) {
			query = query.and(MSF600Rec.mnemonic.equalTo(batchParams.paramMnemonic.trim()))
		}

		int msf600Count = 0
		try {
			edoi.search(query, MAX_ROW_READ, { MSF600Rec msf600Rec->
				msf600Count++
				equipmentRec = msf600Rec
				String equipmentNo = msf600Rec.getPrimaryKey().getEquipNo().trim()
				debug("fitted equipment : ${equipmentNo}")
				retrieveAndExtractFittedEquipment(equipmentNo)
			})
		} catch(Exception e) {
			info("processBrowseFittedEquipment()#searchMSF600 closure was aborted due to: ${e.getMessage()}")
		}
	}

	/**
	 * Read the equipment based on the productive unit.
	 * @param prodUnit productive unit
	 * @return
	 */
	private EquipmentServiceRetrieveReplyDTO readProductiveUnit(String prodUnit) {
		info("readProductiveUnit")

		if(!prodUnit?.trim()) {
			return null
		}

		try{
			EquipmentServiceRetrieveRequestDTO equipRetDto =
					new EquipmentServiceRetrieveRequestDTO()
			equipRetDto.setEquipmentRefSearchMethod("E")
			equipRetDto.setEquipmentRef(prodUnit)
			equipRetDto.setDistrictCode("GRID")
			equipRetDto.setProdUnitFlag(true)
			equipRetDto.setAssocEquipmentItemsExcl(false)
			equipRetDto.setExclInStore(false)
			equipRetDto.setExclScrapSold(false)

			EquipmentServiceRetrieveRequiredAttributesDTO equipReqAtt =
					new EquipmentServiceRetrieveRequiredAttributesDTO()
			equipReqAtt.setReturnEquipmentNo(true)
			equipReqAtt.setReturnEquipmentRef(true)
			equipReqAtt.setReturnParentEquipment(true)
			equipReqAtt.setReturnParentEquipmentRef(true)
			equipReqAtt.setReturnEquipmentGrpId(true)
			equipReqAtt.setReturnEquipmentStatus(true)
			equipReqAtt.setReturnEquipmentClassif0(true)
			equipReqAtt.setReturnEquipmentClassif1(true)

			EquipmentServiceRetrieveReplyCollectionDTO equipReplyDto =
					service.get(SERVICE_EQUIPMENT).retrieve(equipRetDto, equipReqAtt, 1, false)
			return equipReplyDto?.getReplyElements()[0]
		}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
			info("Cannot Retrieve equipment ${SERVICE_EQUIPMENT} : ${e.getMessage()}")
			for(ErrorMessageDTO error : e.errorMessages){
				fieldRef = error.getFieldName()
				errValue = prodUnit
				errMsg = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
				printErrorMsg()
			}
			return null
		}
	}

	/**
	 * Browse Productive Unit Hiearchy.
	 * @param parentEquipNo parent equipment number
	 * @return list of fitted equipment number
	 */
	private ArrayList<String> browseProductiveUnitHierarchy(String parentEquipNo) {
		info("browseProductiveUnitHierarchy")
		ArrayList<String> equipNumbers = new ArrayList<String>()
		QueryImpl query = new QueryImpl(MSFH67Rec.class)
				.and(MSFH67Key.rootEquip.equalTo(parentEquipNo))
				.and(MSFH67Key.equipNo.greaterThanEqualTo(" "))
				.and(MSFH67Key.parentEquip.greaterThanEqualTo(" "))
				.orderBy(MSFH67Rec.msfh67Key)
		edoi.search(query, MAX_ROW_READ, {MSFH67Rec rec->
			equipNumbers.add(rec.getPrimaryKey().getEquipNo().trim())
		})
		return equipNumbers
	}

	/**
	 * retrieve equipment Profile using equipment trace retrieve service
	 * @param equipNo
	 */
	private void retrieveEquipProfiles(String equipNo){
		info("retrieveEquipProfiles ${equipNo}")
		try{
			EquipTraceServiceRetrieveEquipProfilesRequiredAttributesDTO equipProfilesReq =
					new EquipTraceServiceRetrieveEquipProfilesRequiredAttributesDTO()
			equipProfilesReq.setReturnEquipmentNo(true)
			equipProfilesReq.setReturnEquipmentNoFit(true)
			equipProfilesReq.setReturnInstallEquipment(true)
			equipProfilesReq.setReturnInstallEquipmentRef(true)
			equipProfilesReq.setReturnCompCode(true)
			equipProfilesReq.setReturnModCode(true)
			equipProfilesReq.setReturnFittedStatus(true)

			List<EquipTraceServiceRetrieveEquipProfilesReplyDTO> equipProfilesList =
					new ArrayList<EquipTraceServiceRetrieveEquipProfilesReplyDTO>()
			String sRestart = ""
			boolean firstLoop = true
			int count = 0
			while(firstLoop || (sRestart != null && sRestart?.trim())){
				EquipTraceServiceRetrieveEquipProfilesReplyCollectionDTO equipProfilesDTO =
						service.get(SERVICE_EQUIPTRACE).retrieveEquipProfiles(
						{EquipTraceServiceRetrieveEquipProfilesRequestDTO it->
							it.setEGIEquipmentInd("E")
							//it.setEquipmentRef(equipNo)
							it.setEquipmentNo(equipNo)
						}, equipProfilesReq, MAX_INSTANCE,false,sRestart)
				firstLoop = false
				sRestart = equipProfilesDTO.getCollectionRestartPoint()
				equipProfilesList.addAll(equipProfilesDTO.getReplyElements())

				//equipProfilesDTO.getReplyElements().each { EquipTraceServiceRetrieveEquipProfilesReplyDTO eqProf->
				//    retInstallEquipTracing(eqProf.getInstallEquipment(), eqProf.getCompCode(), eqProf.getModCode())
				//}
			}
			extractInstallEquip(equipProfilesList)
		}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
			for(ErrorMessageDTO error : e.errorMessages){
				if(error.getCode().replace("mims.e.","").trim().equals("6449")) {
					browseComponentTracing(equipNo)
				} else {
					info("Cannot Retrieve equipment trace ${SERVICE_EQUIPTRACE} : ${e.getMessage()}")
					fieldRef = error.getFieldName()
					errValue = equipNo
					errMsg   = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
					printErrorMsg()
				}
			}
		}
	}

	/**
	 * Browse component tracing.
	 * @param equipNo
	 */
	private void browseComponentTracing(String equipNo) {
		info("browseComponentTracing")
		QueryImpl qInstalPos = new QueryImpl(MSF650Rec.class)
				.and(MSF650Rec.installPosn.like("${equipNo}%"))
				.columns([MSF650Rec.installPosn])
				.orderBy(MSF650Rec.msf650Key)
		ArrayList<String> processed = new ArrayList<String>()
		edoi.search(qInstalPos, MAX_ROW_READ, {String instalPosn->
			if(!processed.contains(instalPosn)) {
				String compCode = instalPosn.substring(12, 16)
				String modCode = instalPosn.substring(16)
				//retInstallEquipTracing(equipNo, compCode, modCode)
				retInstallEquipTracing(equipNo)
				processed.add(instalPosn)
			}
		})
	}

	/**
	 * retrieve install equipment Tracing using equipment trace retrieve install equipment service
	 * @param installEquipment
	 * @param compCode
	 */
	/*
	 private void retInstallEquipTracing(String installEquipment, String compCode, String modCode){
	 info("retInstallEquipTracing : ${installEquipment} - ${compCode} - ${modCode}")
	 try{
	 EquipTraceServiceRetrieveInstallEquipTracingRequiredAttributesDTO installEquipReq =
	 new EquipTraceServiceRetrieveInstallEquipTracingRequiredAttributesDTO()
	 installEquipReq.setReturnInstallEquipment(true)
	 installEquipReq.setReturnCompCode(true)
	 installEquipReq.setReturnModCode(true)
	 installEquipReq.setReturnFitEquipmentNo(true)
	 List<EquipTraceServiceRetrieveInstallEquipTracingReplyDTO> installEquipList =
	 new ArrayList<EquipTraceServiceRetrieveInstallEquipTracingReplyDTO>()
	 String sRestart   = ""
	 boolean firstLoop = true
	 int count = 0
	 while(firstLoop || (sRestart != null && sRestart.trim())){
	 EquipTraceServiceRetrieveInstallEquipTracingReplyCollectionDTO installEquipDTO =
	 service.get(SERVICE_EQUIPTRACE).retrieveInstallEquipTracing(
	 { EquipTraceServiceRetrieveInstallEquipTracingRequestDTO it->
	 it.setInstallEquipment(installEquipment)
	 it.setCompCode(compCode)
	 it.setModCode(modCode)
	 }, installEquipReq, MAX_INSTANCE, false, sRestart)
	 firstLoop = false
	 sRestart = installEquipDTO.getCollectionRestartPoint()
	 for(EquipTraceServiceRetrieveInstallEquipTracingReplyDTO dto : installEquipDTO.getReplyElements()) {
	 if(!isFitEquipTraceExist(installEquipList, dto)) {
	 installEquipList.add(dto)
	 }
	 }
	 }
	 */
	private void retInstallEquipTracing(String installEquipment){
		info("retInstallEquipTracing : ${installEquipment}")
		try{
			EquipTraceServiceRetrieveEquipProfilesRequiredAttributesDTO installEquipReq =
					new EquipTraceServiceRetrieveEquipProfilesRequiredAttributesDTO()
			installEquipReq.setReturnInstallEquipment(true)
			installEquipReq.setReturnInstallEquipmentRef(true)
			installEquipReq.setReturnEquipmentNoFit(true)
			installEquipReq.setReturnCompCode(true)
			installEquipReq.setReturnModCode(true)

			List<EquipTraceServiceRetrieveEquipProfilesReplyDTO> installEquipList =
					new ArrayList<EquipTraceServiceRetrieveEquipProfilesReplyDTO>()

			String sRestart   = ""
			boolean firstLoop = true
			int count = 0
			while(firstLoop || (sRestart != null && sRestart.trim())){

				EquipTraceServiceRetrieveEquipProfilesReplyCollectionDTO installEquipDTO =
						service.get(SERVICE_EQUIPTRACE).retrieveEquipProfiles(
						{ EquipTraceServiceRetrieveEquipProfilesRequestDTO it->
							it.setEGIEquipmentInd('E')
							it.setEquipmentNo(installEquipment)
						}, installEquipReq, MAX_INSTANCE, false, sRestart)
				firstLoop = false
				sRestart = installEquipDTO.getCollectionRestartPoint()
				for(EquipTraceServiceRetrieveEquipProfilesReplyDTO dto : installEquipDTO.getReplyElements()) {
					if(!isFitEquipTraceExist(installEquipList, dto)) {
						installEquipList.add(dto)
					}
				}
			}

			extractInstallEquip(installEquipList)
		}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
			info("Cannot Retrieve install equipment tracing ${SERVICE_EQUIPTRACE} : ${e.getMessage()}")
			for(ErrorMessageDTO error : e.errorMessages){
				fieldRef = error.getFieldName()
				errValue = installEquipment
				errMsg = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
				printErrorMsg()
			}
		}
	}

	/**
	 * Check if EquipTraceServiceRetrieveInstallEquipTracingReplyDTO already exist
	 * @param list EquipTraceServiceRetrieveInstallEquipTracingReplyDTO
	 * @param equipTraceDTO EquipTraceServiceRetrieveInstallEquipTracingReplyDTO
	 * @return
	 */
	/*
	 private boolean isFitEquipTraceExist(ArrayList<EquipTraceServiceRetrieveInstallEquipTracingReplyDTO> list,
	 EquipTraceServiceRetrieveInstallEquipTracingReplyDTO equipTraceDTO) {
	 debug("isFitEquipTraceExist ${equipTraceDTO.getFitEquipmentNo().trim()}")
	 for(EquipTraceServiceRetrieveInstallEquipTracingReplyDTO dto : list) {
	 if( dto.getFitEquipmentNo().trim().equals(equipTraceDTO.getFitEquipmentNo().trim())) {
	 return true
	 }
	 }
	 return false
	 }
	 */
	private boolean isFitEquipTraceExist(ArrayList<EquipTraceServiceRetrieveEquipProfilesReplyDTO> list,
			EquipTraceServiceRetrieveEquipProfilesReplyDTO equipTraceDTO) {
		debug("isFitEquipTraceExist ${equipTraceDTO.getEquipmentNoFit().trim()}")
		for(EquipTraceServiceRetrieveEquipProfilesReplyDTO dto : list) {
			if( dto.getEquipmentNoFit().trim().equals(equipTraceDTO.getEquipmentNoFit().trim())) {
				return true
			}
		}
		return false
	}

	/**
	 * extract install equipment
	 * than get current fitments equipment using install equipment value
	 * @param installEquipList
	 */
	/*
	 private void extractInstallEquip(List<EquipTraceServiceRetrieveInstallEquipTracingReplyDTO> installEquipList){
	 info("extractInstallEquip")
	 for(EquipTraceServiceRetrieveInstallEquipTracingReplyDTO dto : installEquipList){
	 sFitEquipment = dto.getFitEquipmentNo().trim()
	 sCompCode     = dto.getCompCode().trim()
	 boolean cont = true
	 // filter check from parameter
	 if ((batchParams.paramComponentCode?.trim() && !batchParams.paramComponentCode.trim().equals(sCompCode))
	 || !sFitEquipment?.trim()){
	 cont = false
	 }
	 if(cont){
	 retFitEquipTracing(sFitEquipment)
	 }
	 }
	 }
	 */
	private void extractInstallEquip(List<EquipTraceServiceRetrieveEquipProfilesReplyDTO> installEquipList){
		info("extractInstallEquip")
		for(EquipTraceServiceRetrieveEquipProfilesReplyDTO dto : installEquipList){
			info("Fit Equip ${dto.getEquipmentNoFit().trim()}, Comp Code ${dto.getCompCode().trim()}")
			boolean cont = true
			// filter check from parameter
			if ((batchParams.paramComponentCode?.trim() && !batchParams.paramComponentCode.trim().equals(dto.getCompCode().trim()))
			|| !fetchCurrentFitments(dto).trim()?.trim()){
				cont = false
			}
			if(cont){
				retFitEquipTracing(dto.getEquipmentNoFit().trim())
			}
		}
	}

	private String fetchCurrentFitments(EquipTraceServiceRetrieveEquipProfilesReplyDTO equipReplyProfileDTO){

		EquipTraceServiceFetchCurrentFitmentsRequiredAttributesDTO fetchCurrentAtt = new EquipTraceServiceFetchCurrentFitmentsRequiredAttributesDTO()
		fetchCurrentAtt.setReturnEquipmentRefFit(true)
		fetchCurrentAtt.setReturnEquipmentNoFit(true)

		try{
			EquipTraceServiceFetchCurrentFitmentsReplyDTO detailFetchCurrentFitments =
					service.get(SERVICE_EQUIPTRACE).fetchCurrentFitments(
					{ EquipTraceServiceFetchCurrentFitmentsRequestDTO it->
						it.setInstallEquipment(equipReplyProfileDTO.getInstallEquipment())
						it.setEquipmentRef(equipReplyProfileDTO.getEquipmentRef())
						it.setEquipmentNo(equipReplyProfileDTO.getEquipmentNo())
						it.setEquipmentNoFit(equipReplyProfileDTO.getEquipmentNoFit())
						it.setEGIEquipmentInd(equipReplyProfileDTO.getEGIEquipmentInd())

						it.setEGIEquipmentNumber(equipReplyProfileDTO.getEGIEquipmentNumber())
						it.setModCode(equipReplyProfileDTO.getModCode())
						it.setCompCode(equipReplyProfileDTO.getCompCode())
						it.setFittedStatus(equipReplyProfileDTO.getFittedStatus())
						it.setEquipmentRefFit(equipReplyProfileDTO.getEquipmentRefFit())

					}, fetchCurrentAtt, MAX_INSTANCE, false)

			return detailFetchCurrentFitments.getFitEquipmentNo().trim()

		}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
			info("Cannot Retrieve install equipment tracing ${SERVICE_EQUIPTRACE} : ${e.getMessage()}")
			for(ErrorMessageDTO error : e.errorMessages){
				fieldRef = error.getFieldName()
				errValue = equipReplyProfileDTO.getEquipmentNo()
				errMsg = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
				printErrorMsg()
			}
		}

		return ""

	}
	/**
	 * retrieve fitted equipment tracing using equipment trace service 
	 * @param fitEquipmentNo
	 */
	private void retFitEquipTracing(String fittedEquipNo){
		info("retFitEquipTracing : ${fittedEquipNo}")
		try{
			List<EquipTraceServiceRetrieveFitEquipTracingReplyDTO> fitEquipList =
					new ArrayList<EquipTraceServiceRetrieveFitEquipTracingReplyDTO>()
			String sRestart = ""
			boolean firstLoop = true
			int count = 0
			while(firstLoop || (sRestart != null && sRestart.trim())){
				EquipTraceServiceRetrieveFitEquipTracingRequiredAttributesDTO fitEquipAtt =
						new EquipTraceServiceRetrieveFitEquipTracingRequiredAttributesDTO()
				fitEquipAtt.setReturnFitEquipmentNo(true)
				fitEquipAtt.setReturnFitEquipmentRef(true)
				fitEquipAtt.setReturnActionDesc(true)
				fitEquipAtt.setReturnTraceActn(true)
				fitEquipAtt.setReturnETDate(true)
				fitEquipAtt.setReturnOrigRefNum(true)
				fitEquipAtt.setReturnOrigRefType(true)
				fitEquipAtt.setReturnInstallEquipment(true)
				fitEquipAtt.setReturnInstallEquipmentRef(true)
				fitEquipAtt.setReturnInstallEquipmentDesc(true)
				fitEquipAtt.setReturnEquipmentRef(true)
				fitEquipAtt.setReturnCompCode(true)
				fitEquipAtt.setReturnModCode(true)

				EquipTraceServiceRetrieveFitEquipTracingReplyCollectionDTO fitEquipDTO =
						service.get(SERVICE_EQUIPTRACE).retrieveFitEquipTracing(
						{EquipTraceServiceRetrieveFitEquipTracingRequestDTO it->
							it.setFitEquipmentNo(fittedEquipNo)
						}, fitEquipAtt,MAX_INSTANCE,false,sRestart)
				firstLoop = false
				sRestart = fitEquipDTO.getCollectionRestartPoint()
				fitEquipList.addAll(fitEquipDTO.getReplyElements())
			}
			extractFitEquip(fitEquipList)
		}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
			info("Cannot Retrieve fitted equipment tracing ${SERVICE_EQUIPTRACE} : ${e.getMessage()}")
			for(ErrorMessageDTO error : e.errorMessages){
				fieldRef = error.getFieldName()
				errValue = fittedEquipNo
				errMsg   = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
				printErrorMsg()
			}
		}
	}

	/**
	 * Retrieve and extract fitted equipment tracing using equipment trace service
	 * @param fitEquipmentNo
	 */
	private void retrieveAndExtractFittedEquipment(String fittedEquipNo){
		info("retrieveAndExtractFittedEquipment : ${fittedEquipNo}")
		try{
			List<EquipTraceServiceRetrieveFitEquipTracingReplyDTO> fitEquipList =
					new ArrayList<EquipTraceServiceRetrieveFitEquipTracingReplyDTO>()
			String sRestart = ""
			boolean firstLoop = true
			int count = 0
			while(firstLoop || (sRestart != null && sRestart.trim())){
				EquipTraceServiceRetrieveFitEquipTracingRequiredAttributesDTO fitEquipAtt =
						new EquipTraceServiceRetrieveFitEquipTracingRequiredAttributesDTO()
				fitEquipAtt.setReturnFitEquipmentNo(true)
				fitEquipAtt.setReturnFitEquipmentRef(true)
				fitEquipAtt.setReturnActionDesc(true)
				fitEquipAtt.setReturnTraceActn(true)
				fitEquipAtt.setReturnETDate(true)
				fitEquipAtt.setReturnOrigRefNum(true)
				fitEquipAtt.setReturnOrigRefType(true)
				fitEquipAtt.setReturnInstallEquipment(true)
				fitEquipAtt.setReturnInstallEquipmentRef(true)
				fitEquipAtt.setReturnInstallEquipmentDesc(true)
				fitEquipAtt.setReturnEquipmentRef(true)
				fitEquipAtt.setReturnCompCode(true)
				fitEquipAtt.setReturnModCode(true)

				EquipTraceServiceRetrieveFitEquipTracingReplyCollectionDTO fitEquipDTO =
						service.get(SERVICE_EQUIPTRACE).retrieveFitEquipTracing(
						{EquipTraceServiceRetrieveFitEquipTracingRequestDTO it->
							it.setFitEquipmentNo(fittedEquipNo)
						}, fitEquipAtt,MAX_INSTANCE,false,sRestart)
				firstLoop = false
				sRestart = fitEquipDTO.getCollectionRestartPoint()
				fitEquipList.addAll(fitEquipDTO.getReplyElements())
			}
			extractFitEquip(fitEquipList)
		}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
			info("Cannot Retrieve fitted equipment tracing ${SERVICE_EQUIPTRACE} : ${e.getMessage()}")
			for(ErrorMessageDTO error : e.errorMessages){
				fieldRef = error.getFieldName()
				errValue = fittedEquipNo
				errMsg   = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
				printErrorMsg()
			}
		}
	}

	/**
	 * Extract fitted equipment then get mnemonic and name plate
	 * @param fitEquipList
	 */
	private void extractFitEquip(List<EquipTraceServiceRetrieveFitEquipTracingReplyDTO> fitEquipList){
		info("extractFitEquip")
		String etDate
		boolean lastFlag = true
		boolean firstMax = true
		boolean firstMin = true

		trt601CSVDetail = new TRT601Obj ()

		EquipTraceServiceRetrieveFitEquipTracingReplyDTO fitEquipment
		//browse the tracing action history
		for(EquipTraceServiceRetrieveFitEquipTracingReplyDTO fitEquip : fitEquipList){
			etDate = calendarToString(fitEquip.getETDate())
			//find last tracing action
			if(firstMax || etDate > trt601CSVDetail.getLastTrcDate()){
				firstMax = false
				trt601CSVDetail.setLastTrcDate(etDate)
				trt601CSVDetail.setLastActionDesc(fitEquip.getActionDesc().trim())
				trt601CSVDetail.setLastAction(fitEquip.getTraceActn().trim())
				fitEquipment = (EquipTraceServiceRetrieveFitEquipTracingReplyDTO) BeanUtils.cloneBean(fitEquip)
			}
			//find the earliest tracing action
			if(firstMin || etDate < trt601CSVDetail.getFirstTrcDate()){
				firstMin = false
				trt601CSVDetail.setFirstTrcDate(etDate)
				trt601CSVDetail.setOrigRefNum(fitEquip.getOrigRefNum())
				trt601CSVDetail.setOrigRefType(fitEquip.getOrigRefType())
			}
		}
		if(fitEquipment != null
		&& (!batchParams.paramTracingAction?.trim()) ||
		batchParams.paramTracingAction.trim().equals(trt601CSVDetail.getLastAction())) {
			fetchFixedEquipment(fitEquipment)
			if(readMneumonic(fitEquipment.getFitEquipmentNo())){
				retrieveNamePlate(fitEquipment.getFitEquipmentNo())
				info("getInstallEquipmentRef2:" + fitEquipment.getInstallEquipmentRef())
				writeCsvReport()
			}
		}
	}

	/**
	 * Fetch Fixed Equipment information from the fitted.
	 * @param fitEquipment fitted equipment
	 */
	private boolean fetchFixedEquipment(EquipTraceServiceRetrieveFitEquipTracingReplyDTO fitEquipment) {
		info("getInstallEquipmentRef:" + fitEquipment.getInstallEquipmentRef())
		trt601CSVDetail.setsFitEquipment(fitEquipment.getFitEquipmentNo().trim())
		trt601CSVDetail.setsCompCode(fitEquipment.getCompCode().trim())
		trt601CSVDetail.setsModCode(fitEquipment.getModCode().trim())

		//if not defit, populate the parent details
		if(!DEFITMENT_STATUS.contains(fitEquipment.getTraceActn().trim().toUpperCase())) {
			trt601CSVDetail.setsInstallEquipRef(fitEquipment.getInstallEquipmentRef()?.trim())
			trt601CSVDetail.setsInstallEquipmentDesc(fitEquipment.getInstallEquipmentDesc()?.trim())

			EquipmentServiceReadReplyDTO equipDTO = readEquipment(fitEquipment.getInstallEquipment())
			if(equipDTO) {
				trt601CSVDetail.setsEquipStatus(equipDTO.getEquipmentStatus())
				trt601CSVDetail.setsRegCategoryDesc(fetchRegCategoryDescription(equipDTO.getEquipmentClassif0().trim()))
				trt601CSVDetail.setsCustomerDesc(fetchCustomerDescription(equipDTO.getEquipmentClassif1().trim()))
				return true
			}
		}
		return false
	}


	/**
	 * get equipment using equipment service read
	 * @param equipNo
	 */
	private EquipmentServiceReadReplyDTO readEquipment(String equipNo){
		info("readEquipment ${equipNo}")

		if(!equipNo?.trim()) {
			return null
		}

		EquipmentServiceReadReplyDTO retEquip = null
		try{
			EquipmentServiceReadRequiredAttributesDTO equipReq =
					new EquipmentServiceReadRequiredAttributesDTO()
			equipReq.setReturnEquipmentNo(true)
			equipReq.setReturnEquipmentRef(true)
			equipReq.setReturnParentEquipment(true)
			equipReq.setReturnParentEquipmentRef(true)
			equipReq.setReturnTraceableFlg(true)
			equipReq.setReturnEquipmentStatus(true)
			equipReq.setReturnCompCode(true)
			equipReq.setReturnEquipmentNoDescription1(true)
			equipReq.setReturnEquipmentClassif0(true)
			equipReq.setReturnEquipmentClassif1(true)
			equipReq.setReturnEquipmentClassif2(true)
			EquipmentServiceReadReplyDTO equipDTO =
					service.get(SERVICE_EQUIPMENT).read(
					{EquipmentServiceReadRequestDTO it->
						it.setEquipmentNo(equipNo)
						it.setRequiredAttributes(equipReq)
					})
			return equipDTO
		}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
			info("Cannot read equipment service ${SERVICE_EQUIPMENT} : ${e.getMessage()}")
			for(ErrorMessageDTO error : e.errorMessages){
				fieldRef = error.getFieldName()
				errValue = equipNo
				errMsg = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
				printErrorMsg()
			}
			return null
		}
	}

	/**
	 * get mneumonic equipment using equipment service read
	 * @param fitEquipNo
	 */
	private boolean readMneumonic(String fitEquipNo){
		info("readMneumonic : ${fitEquipNo}")

		if(!fitEquipNo?.trim()) {
			return null
		}

		try{
			EquipmentServiceReadRequiredAttributesDTO equipReq =
					new EquipmentServiceReadRequiredAttributesDTO()
			equipReq.setReturnEquipmentNo(true)
			equipReq.setReturnMnemonic(true)
			equipReq.setReturnOriginalDoc(true)
			equipReq.setReturnPartNo(true)
			equipReq.setReturnEquipmentGrpId(true)
			equipReq.setReturnSerialNumber(true)
			EquipmentServiceReadReplyDTO equipDTO =
					service.get(SERVICE_EQUIPMENT).read(
					{EquipmentServiceReadRequestDTO it->
						it.setEquipmentNo(fitEquipNo)
						it.setRequiredAttributes(equipReq)
					})
			trt601CSVDetail.setsMnemonic(equipDTO.getMnemonic().trim())
			trt601CSVDetail.setsOriginalDoc(equipDTO.getOriginalDoc().trim())
			trt601CSVDetail.setsPartNo(equipDTO.getPartNo().trim())
			trt601CSVDetail.setsEquipmentGrpId(equipDTO.getEquipmentGrpId().trim())
			trt601CSVDetail.setsSerialNum(equipDTO.getSerialNumber().trim())

			if((batchParams.paramEquipGroupId?.trim() && !batchParams.paramEquipGroupId.trim().equals(trt601CSVDetail.getsEquipmentGrpId()))
			|| (batchParams.paramOriginatingDoc?.trim() && !batchParams.paramOriginatingDoc.trim().equals( trt601CSVDetail.getsOriginalDoc()))
			|| (batchParams.paramMnemonic?.trim() && !batchParams.paramMnemonic.trim().equals(trt601CSVDetail.getsMnemonic()))) {
				return false
			}
			return true
		}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
			info("Cannot read equipment service ${SERVICE_EQUIPMENT} : ${e.getMessage()}")
			for(ErrorMessageDTO error : e.errorMessages){
				fieldRef = error.getFieldName()
				errValue = fitEquipNo
				errMsg = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
				printErrorMsg()
			}
			return false
		}
	}

	/**
	 * retrieve nameplate for the fitted equipment using nameplate service
	 * @param fitEquipNo
	 */
	private void retrieveNamePlate(String fitEquipNo){
		info("retrieveNamePlate ${fitEquipNo}")

		if(fitEquipNo?.trim()) {
			try{
				List<NameplateServiceRetrieveReplyDTO> namePlateList =
						new ArrayList<NameplateServiceRetrieveReplyDTO>()

				String sRestart = ""
				boolean firstLoop = true
				NameplateServiceRetrieveRequiredAttributesDTO namePlateReq =
						new NameplateServiceRetrieveRequiredAttributesDTO()
				namePlateReq.setReturnEquipmentNo(true)
				namePlateReq.setReturnAttributeName(true)
				namePlateReq.setReturnAttributeValue(true)
				while(firstLoop || (sRestart != null && sRestart.trim())){
					NameplateServiceRetrieveReplyCollectionDTO namePlateDTO =
							service.get(SERVICE_NAMEPLATE).retrieve(
							{NameplateServiceRetrieveRequestDTO it->
								it.setEquipmentNo(fitEquipNo)
							}, namePlateReq, MAX_INSTANCE,false,sRestart)
					firstLoop = false
					sRestart = namePlateDTO.getCollectionRestartPoint()

					info("extractNamePlate")
					namePlateDTO.getReplyElements().each {NameplateServiceRetrieveReplyDTO namePlate->
						if(namePlate.getAttributeName().trim().equals("VNOMINAL")){
							trt601CSVDetail.setsVnominal(namePlate.getAttributeValue())
						}else if(namePlate.getAttributeName().trim().equals("ILOAD")){
							trt601CSVDetail.setsIload(namePlate.getAttributeValue())
						}else if(namePlate.getAttributeName().trim().equals("IFAULT")){
							trt601CSVDetail.setsIfault(namePlate.getAttributeValue())
						}else if(namePlate.getAttributeName().trim().equals("IFAULTT")){
							trt601CSVDetail.setsIfaultt(namePlate.getAttributeValue())
						}else if(namePlate.getAttributeName().trim().equals("INSULATION")){
							trt601CSVDetail.setsInsulation(namePlate.getAttributeValue())
						}else if(namePlate.getAttributeName().trim().equals("TYPE")){
							trt601CSVDetail.setsType(namePlate.getAttributeValue())
						}else if(namePlate.getAttributeName().trim().equals("INSULMASS")){
							trt601CSVDetail.setsInsulMass(namePlate.getAttributeValue())
						}
					}
				}
			}catch(com.mincom.enterpriseservice.exception.EnterpriseServiceOperationException e){
				info("Cannot retrieve name plate service ${SERVICE_NAMEPLATE} : ${e.getMessage()}")
				for(ErrorMessageDTO error : e.errorMessages){
					fieldRef = error.getFieldName()
					errValue = fitEquipNo
					errMsg = error.getCode().replace("mims.e.","") + "-" + error.getMessage()
					printErrorMsg()
				}
			}
		}
	}

	/**
	 * Read table based on table type and table code.
	 * @param type table type
	 * @param code table code
	 * @return MSF010Rec
	 */
	private MSF010Rec readTable(String type, String code) {
		info("readTable ${type} - ${code}")
		MSF010Key tableKey = new MSF010Key(type, code)
		try {
			return edoi.findByPrimaryKey(tableKey)
		} catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e) {
			info("Table ${type} - ${code} does not exist.")
			return null
		}
	}

	/**
	 * Populate Customer Type Description
	 */
	private void populateCustomerMap() {
		info("populateCustomerMap")
		QueryImpl qCust = new QueryImpl(MSF010Rec.class)
				.and(MSF010Key.tableType.equalTo("E1"))
				.and(MSF010Key.tableCode.greaterThanEqualTo(" "))
		edoi.search(qCust) {MSF010Rec rec->
			customerDescriptionMap.put(rec.getPrimaryKey().getTableCode().trim(),
					rec.getTableDesc().trim())
		}
	}

	/**
	 * Populate Reg Category Description
	 */
	private void populateRegCategoryMap() {
		info("populateRegCategoryMap")
		QueryImpl qCust = new QueryImpl(MSF010Rec.class)
				.and(MSF010Key.tableType.equalTo("E0"))
				.and(MSF010Key.tableCode.greaterThanEqualTo(" "))
		edoi.search(qCust) {MSF010Rec rec->
			regulatoryCategoryMap.put(rec.getPrimaryKey().getTableCode().trim(),
					rec.getTableDesc().trim())
		}
	}

	/**
	 * Fetch customer description, if not exist in the map, add it.
	 * @param code customer code
	 * @return customer description
	 */
	private String fetchCustomerDescription(String code) {
		info("fetchCustomerDescription")
		String desc = customerDescriptionMap.get(code.trim())

		if(!desc?.trim()) {
			MSF010Rec tableRec = readTable("E1", code.trim())
			if(tableRec != null) {
				desc = tableRec.getTableDesc().trim()
				customerDescriptionMap.put(code.trim(), desc)
			}
		}
		return desc
	}

	/**
	 * Fetch regulatory category description, if not exist in the map, add it.
	 * @param code regulatory category code
	 * @return regulatory category description
	 */
	private String fetchRegCategoryDescription(String code) {
		info("fetchRegCategoryDescription")
		String desc = regulatoryCategoryMap.get(code.trim())

		if(!desc?.trim()) {
			MSF010Rec tableRec = readTable("E0", code.trim())
			if(tableRec != null) {
				desc = tableRec.getTableDesc()?.trim()
				regulatoryCategoryMap.put(code.trim(), desc)
			}
		}
		return desc
	}

	/**
	 * convert Calendar type of date to String value
	 * with format yyyyMMdd
	 * @param calDate
	 * @return String
	 */
	private String calendarToString(Calendar calDate){
		info("calendarToString")
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd")
		sdf.setLenient(false)
		if(sdf.format(calDate.getTime()) >= MIN_DATE){
			return sdf.format(calDate.getTime())
		}else{
			return "00000000"
		}
	}

	/**
	 * Convert the date format with specified separator <code>(YY/MM/DD)</code>.
	 * @param dateS date as a String
	 * @return formatted date as a String
	 */
	private String convertDateFormat(String dateS) {
		info("convertDateFormat")
		if(dateS?.trim()){
			dateS = dateS.padLeft(8).replace(" ", "0")
		}else{
			dateS = "00000000"
		}
		return dateS.substring(6) + "/" + dateS.substring(4,6) + "/" + dateS.substring(0,4)
	}
	/**
	 * create csv report in every fitted equipment
	 * @param fitEquipDTO
	 */
	private void writeCsvReport(){
		info("writeCsvReport")
		if(firstCsv){
			firstCsv = false
			def workingDir = env.workDir
			String taskUUID = this.getTaskUUID()
			String inputFilePath = "${workingDir}/${CSV_TRT601}"
			String outputFilePath = "${workingDir}/${CSV_TRT601}"
			if(taskUUID?.trim()){
				outputFilePath = outputFilePath + "." + taskUUID
			}
			outputFilePath = outputFilePath + ".csv"
			trt601File = new File(outputFilePath)
			info("${CSV_TRT601} created in ${trt601File.getAbsolutePath()}")
			csvTrt601Writer = new BufferedWriter(new FileWriter(trt601File))
			StringBuilder header = new StringBuilder()
			header.append("Equipment Reference".padRight(30))
			header.append(",")
			header.append("Equipment Description".padRight(40))
			header.append(",")
			header.append("Equipment Status")
			header.append(",")
			header.append("Regulatory Category Description".padRight(40))
			header.append(",")
			header.append("Customer Description".padRight(40))
			header.append(",")
			header.append("PIC Number".padRight(30))
			header.append(",")
			header.append("Manufacturer")
			header.append(",")
			header.append("Cont.No.".padRight(10))
			header.append(",")
			header.append("Manufacturer Type".padRight(30))
			header.append(",")
			header.append("EGI".padRight(12))
			header.append(",")
			header.append("Comp Code")
			header.append(",")
			header.append("Mod Code")
			header.append(",")
			header.append("Volts".padRight(10))
			header.append(",")
			header.append("Load I".padRight(10))
			header.append(",")
			header.append("Fault I".padRight(10))
			header.append(",")
			header.append("Fault Sec".padRight(10))
			header.append(",")
			header.append("Insulation")
			header.append(",")
			header.append("Type".padRight(10))
			header.append(",")
			header.append("Gas Mass".padRight(10))
			header.append(",")
			header.append("First Tracing Action Date")
			header.append(",")
			header.append("Last Tracing Action Date")
			header.append(",")
			header.append("Reference")
			header.append(",")
			header.append("Reference Type")
			header.append(",")
			header.append("Serial Number".padRight(30))
			header.append(",")
			header.append("Last Tracing Action")
			csvTrt601Writer.write(header.toString())
			csvTrt601Writer.write("\r\n")
		}

		StringBuilder row = new StringBuilder()
		row.append("${trt601CSVDetail.getsInstallEquipRef()?.padRight(30).substring(0, 30)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsInstallEquipmentDesc()?.padRight(40).substring(0, 40)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsEquipStatus()?.padRight(2)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsRegCategoryDesc()?.padRight(40)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsCustomerDesc()?.padRight(40)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsFitEquipment()?.padRight(30)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsMnemonic()?.padRight(8)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsOriginalDoc()?.padRight(10)}")
		row.append(",")
		row.append("\'${trt601CSVDetail.getsPartNo()?.padRight(30)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsEquipmentGrpId()?.padRight(12)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsCompCode()?.padRight(4)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsModCode()?.padRight(2)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsVnominal()?.padRight(10)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsIload()?.padRight(10)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsIfault()?.padRight(10)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsIfaultt()?.padRight(10)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsInsulation()?.padRight(10)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsType()?.padRight(10)}")
		row.append(",")
		row.append("${trt601CSVDetail.getsInsulMass()?.padRight(10)}")
		row.append(",")
		row.append("${convertDateFormat(trt601CSVDetail.getFirstTrcDate())}")
		row.append(",")
		row.append("${convertDateFormat(trt601CSVDetail.getLastTrcDate())}")
		row.append(",")
		row.append("${trt601CSVDetail.getOrigRefNum()?.padRight(12)}")
		row.append(",")
		row.append("${trt601CSVDetail.getOrigRefType()?.padRight(2)}")
		row.append(",")
		row.append("\'${trt601CSVDetail.getsSerialNum()?.padRight(30)}")
		row.append(",")
		row.append("${trt601CSVDetail.getLastActionDesc()?.padRight(14)}")

		csvTrt601Writer.write(row.toString())
		csvTrt601Writer.write("\r\n")
	}

	/**
	 * print error msg in every error that occurs in service call
	 */
	private void printErrorMsg(){
		info("printErrorMsg")
		if (firstErr){
			reportA = report.open(REPORT_NAME)
			reportA.write("${REPORT_NAME} Summary Error Report".center(132))
			reportA.writeLine(132,"-")
			reportA.write("Field Ref/ Value".padRight(50) + "Error/ Warning Message")
			reportA.writeLine(132,"-")
			firstErr = false
			bReportAOpen = true;
		}
		reportA.write((fieldRef + (errValue?.trim() ? "/" : "") + errValue).padRight(60) + errMsg)
	}

	private void printBatchReport(){
		info("printBatchReport")
		//print batch report
		if(bReportAOpen){
			reportA.close();
		}
		if(csvTrt601Writer != null){
			csvTrt601Writer.close()
			info("Adding TRT601 CSV into Request.")
			if (taskUUID?.trim()) {
				request.request.CURRENT.get().addOutput(trt601File,
						"text/comma-separated-values", CSV_TRT601);
			}
		}
	}
}

/*run script*/  
ProcessTrb601 process = new ProcessTrb601();
process.runBatch(binding);
