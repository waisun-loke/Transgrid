package com.mincom.ellipse.script.custom;

import groovy.lang.Binding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.mincom.batch.script.Params;
import com.mincom.batch.script.Reports;
import com.mincom.batch.script.RequestInterface
import com.mincom.batch.script.Restart;
import com.mincom.batch.script.Sort;
import com.mincom.ellipse.script.util.CommAreaScriptWrapper;
import com.mincom.batch.environment.BatchEnvironment;
import com.mincom.ellipse.edoi.ejb.msf000.MSF000_DC0004Rec;
import com.mincom.ellipse.edoi.ejb.msf000.MSF000_DC0004Key;
import com.mincom.ellipse.edoi.ejb.msf010.MSF010Key
import com.mincom.ellipse.edoi.ejb.msf010.MSF010Rec
import com.mincom.ellipse.edoi.ejb.msf200.MSF200Key;
import com.mincom.ellipse.edoi.ejb.msf200.MSF200Rec
import com.mincom.ellipse.edoi.ejb.msf723.MSF723Key;
import com.mincom.ellipse.edoi.ejb.msf723.MSF723Rec
import com.mincom.ellipse.edoi.ejb.msf760.MSF760Key;
import com.mincom.ellipse.edoi.ejb.msf760.MSF760Rec
import com.mincom.ellipse.edoi.ejb.msf801.MSF801Rec
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_A_801Rec
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_A_801Key;
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_C0_801Key;
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_C0_801Rec
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_CD_801Key;
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_CD_801Rec
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_PG_801Rec;
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_PG_801Key;
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_R_801Key;
import com.mincom.ellipse.edoi.ejb.msf801.MSF801_R_801Rec;
import com.mincom.ellipse.edoi.ejb.msf80a.MSF80AKey;
import com.mincom.ellipse.edoi.ejb.msf80a.MSF80ARec
import com.mincom.ellipse.edoi.ejb.msf810.MSF810Key;
import com.mincom.ellipse.edoi.ejb.msf810.MSF810Rec
import com.mincom.ellipse.edoi.ejb.msf820.MSF820Key;
import com.mincom.ellipse.edoi.ejb.msf820.MSF820Rec
import com.mincom.ellipse.edoi.ejb.msf822.MSF822Key;
import com.mincom.ellipse.edoi.ejb.msf822.MSF822Rec
import com.mincom.ellipse.edoi.ejb.msf823.MSF823Key;
import com.mincom.ellipse.edoi.ejb.msf823.MSF823Rec;
import com.mincom.ellipse.edoi.ejb.msf837.MSF837Key;
import com.mincom.ellipse.edoi.ejb.msf837.MSF837Rec;
import com.mincom.ellipse.edoi.ejb.msf839.MSF839Key;
import com.mincom.ellipse.edoi.ejb.msf839.MSF839Rec;
import com.mincom.ellipse.edoi.ejb.msf880.MSF880Key;
import com.mincom.ellipse.edoi.ejb.msf880.MSF880Rec
import com.mincom.ellipse.edoi.ejb.msf8p1.MSF8P1Key
import com.mincom.ellipse.edoi.ejb.msf8p1.MSF8P1Rec
import com.mincom.ellipse.edoi.ejb.msf8p2.MSF8P2Key
import com.mincom.ellipse.edoi.ejb.msf8p2.MSF8P2Rec
import com.mincom.ellipse.edoi.ejb.msf8p3.MSF8P3Key
import com.mincom.ellipse.edoi.ejb.msf8p3.MSF8P3Rec
import com.mincom.ellipse.edoi.ejb.msf8p4.MSF8P4Rec
import com.mincom.ellipse.edoi.ejb.msf8p4.MSF8P4Key
import com.mincom.ellipse.edoi.ejb.msf8p5.MSF8P5Key
import com.mincom.ellipse.edoi.ejb.msf8p5.MSF8P5Rec
import com.mincom.ellipse.edoi.ejb.msf8p6.MSF8P6Key
import com.mincom.ellipse.edoi.ejb.msf8p6.MSF8P6Rec
import com.mincom.ellipse.edoi.ejb.msf8p7.MSF8P7Rec
import com.mincom.ellipse.edoi.ejb.msf8p8.MSF8P8Rec
import com.mincom.ellipse.edoi.ejb.msf8p9.MSF8P9Key
import com.mincom.ellipse.edoi.ejb.msf8p9.MSF8P9Rec
import com.mincom.ellipse.edoi.ejb.msf8pa.MSF8PAKey
import com.mincom.ellipse.edoi.ejb.msf8pa.MSF8PARec
import com.mincom.ellipse.edoi.ejb.msf8pb.MSF8PBKey
import com.mincom.ellipse.edoi.ejb.msf8pb.MSF8PBRec
import com.mincom.ellipse.edoi.ejb.msf8pc.MSF8PCKey
import com.mincom.ellipse.edoi.ejb.msf8pc.MSF8PCRec
import com.mincom.ellipse.edoi.ejb.msf8pd.MSF8PDKey
import com.mincom.ellipse.edoi.ejb.msf8pd.MSF8PDRec
import com.mincom.ellipse.edoi.ejb.msf8pe.MSF8PERec
import com.mincom.ellipse.script.util.BatchWrapper;
import com.mincom.ellipse.script.util.CommAreaScriptWrapper;
import com.mincom.ellipse.script.util.EDOIWrapper;
import com.mincom.ellipse.script.util.EROIWrapper;
import com.mincom.ellipse.script.util.ServiceWrapper;
import com.mincom.eql.Constraint
import com.mincom.eql.StringConstraint
import com.mincom.eql.impl.QueryImpl
import com.mincom.eql.common.Rec

public class ParamsAABPAY{
    //List of Input Parameters
    String paramPayGroup = "";
    String paramPayRunType = "";
}
public class ProcessAABPAY  extends SuperBatch {
    /*
     * IMPORTANT!
     * Update this Version number EVERY push to GIT
     */
    private version = 22;
    private ParamsAABPAY batchParams;
    private AABPAYReportElement reportElement;

    public void runBatch(Binding b){

        init(b);
        info("AABPAY version:" + version)
        batchParams = params.fill(new ParamsAABPAY())
        try {
            processBatch(b);
            printBatchReport();
        }
        finally {
            info("Finish ...")
        }
    }

	private void delay() {
		info("Delay")
		
		try {
    		Thread.sleep(18000);
		} catch(InterruptedException ex) {
    		Thread.currentThread().interrupt();
		}

	}



    private void processBatch(Binding b){
        info("processBatch");

        String sMST82Zpath = env.getWorkDir().toString()+"/MST82X"
        String taskUuid = Boolean.getBoolean("mincom.groovy.classes") ? "" : request.request.getTaskUuid();
        if(taskUuid?.trim()){
            sMST82Zpath = sMST82Zpath +"."+taskUuid;
        }
        info ("Opening:"+sMST82Zpath)

        AABPAY_Counter msfCounter = new AABPAY_Counter()

        AABPAYProcessMsf8p m8pSeries = new AABPAYProcessMsf8p()
        m8pSeries.initProcess(b,batchParams.paramPayRunType)

        BigDecimal iIndex = 1

        File inputFile = new File(sMST82Zpath)

        inputFile.eachLine {String lineRead ->
            if (StringUtils.hasText(lineRead.trim())){
                info ("Processing Line:" + iIndex.toString())
                delay()
                m8pSeries.populateFields(lineRead,iIndex)
                iIndex++
            }
        }

        if (m8pSeries.getM8pRecObjPopulated()){
            m8pSeries.writeToDatabase()
        }
        info("Finished Processing Record")
        this.reportElement = m8pSeries.getReportElement()
        this.reportElement.setTotalLineProcessed(iIndex)
    }

    private void printBatchReport(){
        info("printBatchReport")
        //print batch report
        AABPAY_Counter msfCounter = this.reportElement.getMsfCounter()
        Integer iTotalProcessed = this.reportElement.getTotalLineProcessed() - 1
        List <String> errorLine = this.reportElement.getErrorLine()

        def AABPAYa = report.open("AABPAY");

        if (!errorLine.isEmpty()){
            errorLine.each {String stringLine ->
                AABPAYa.write(stringLine)
            }
        }
        AABPAYa.write("Processed line :" + iTotalProcessed.toString())
        AABPAYa.write("MSF8P1 created:" + msfCounter.getiMSF8P1().toString()+ " Record(s)")
        AABPAYa.write("MSF8P2 created:" + msfCounter.getiMSF8P2().toString()+ " Record(s)")
        AABPAYa.write("MSF8P3 created:" + msfCounter.getiMSF8P3().toString()+ " Record(s)")
        AABPAYa.write("MSF8P4 created:" + msfCounter.getiMSF8P4().toString()+ " Record(s)")
        AABPAYa.write("MSF8P5 created:" + msfCounter.getiMSF8P5().toString()+ " Record(s)")
        AABPAYa.write("MSF8P6 created:" + msfCounter.getiMSF8P6().toString()+ " Record(s)")
        AABPAYa.write("MSF8P7 created:" + msfCounter.getiMSF8P7().toString()+ " Record(s)")
        AABPAYa.write("MSF8P8 created:" + msfCounter.getiMSF8P8().toString()+ " Record(s)")
        AABPAYa.write("MSF8P9 created:" + msfCounter.getiMSF8P9().toString()+ " Record(s)")
        AABPAYa.write("MSF8PA created:" + msfCounter.getiMSF8PA().toString()+ " Record(s)")
        AABPAYa.write("MSF8PB created:" + msfCounter.getiMSF8PB().toString()+ " Record(s)")
        AABPAYa.write("MSF8PC created:" + msfCounter.getiMSF8PC().toString()+ " Record(s)")
        AABPAYa.write("MSF8PD created:" + msfCounter.getiMSF8PD().toString()+ " Record(s)")
        AABPAYa.write("MSF8PE created:" + msfCounter.getiMSF8PE().toString()+ " Record(s)")
        AABPAYa.close()

    }
}

public class AABPAYProcessMsf8p{
    private String sEmpId = null
    private String sPaySlipType = null
    private String sPayGroup
    private AABPAYM8pCommonData commonData
    private AABPAYCommonMethod commonMethd
    private AABPAYMsf8pSeriesRec m8pRec
    private MSF8P1Rec msf8P1Rec
    private MSF8P4Rec msf8p4Rec
    private AABPAY_Counter msfCounter
    private AABPAYReportElement reportElement
    private List <String> errorString = new ArrayList <String>()
    private Integer iMsf8p4Index = 0
    private boolean m8pRecObjPopulated = false

    public boolean getM8pRecObjPopulated (){
        return this.m8pRecObjPopulated
    }

    public void initProcess(Binding b,String payRunType){
        this.commonData = new AABPAYM8pCommonData()
        this.commonMethd = new AABPAYCommonMethod()
        this.commonMethd.info("initProcess", getClass())
        CommAreaScriptWrapper commarea
        commarea = b.getVariable("commarea")
        this.commonData.setSessionBinding(b)
        this.commonData.setLastModUser(commarea.UserId)
        this.commonData.setLastModDate(commarea.TodaysDate)
        this.commonData.setLastModTime(commarea.Time)
        this.commonData.setConsYTDTots(commarea.ConsYtdTots)
        this.commonData.setPayRunType(payRunType)
        this.msfCounter = new AABPAY_Counter()
        this.reportElement = new AABPAYReportElement()
        this.msfCounter.initCounter()
    }

    public AABPAYReportElement getReportElement(){
        this.commonMethd.info("getReportElement", getClass())
        this.reportElement.setMsfCounter(this.msfCounter)
        this.reportElement.setErrorLine(this.errorString)
        return this.reportElement
    }

    public void populateFields(String inputString, BigDecimal iLineNo){
        ArrayList <String> m82xRec = this.commonMethd.constructInputList(inputString)
        AABPAYMst82xRec m82x = new AABPAYMst82xRec()
        boolean sameGroupOfRecord = false;
        AABPAYMSF8P1Rec msf8p1Obj
        AABPAYMSF8P4Rec msf8p4Obj

        this.commonMethd.info("populateFields", getClass())

        if((this.sEmpId.equals(m82xRec[m82x.M82X_EMPLOYEE_ID]))
        &&(this.sPaySlipType.equals(m82xRec[m82x.M82X_PAYSLIP_TYPE]))){
            sameGroupOfRecord = true
        }else{

            sameGroupOfRecord = false
        }

        switch (m82xRec[m82x.M82X_RECORD_TYPE]){

            case m82x.A_RECORD:
                this.commonMethd.info("populateFields - Processing A Record", getClass())
                if (this.sPayGroup.equals(m82xRec[m82x.M82X_PAY_GROUP_A])){
                    //do something for same paygroup
                }else{
                    if (m8pRecObjPopulated){
                        writeToDatabase()
                    }
                    this.m8pRecObjPopulated = true
                    this.m8pRec = new AABPAYMsf8pSeriesRec()
                    this.m8pRec.initMsf8PSeriesRec()
                    msf8p1Obj = new AABPAYMSF8P1Rec()
                    this.sPayGroup = m82xRec[m82x.M82X_PAY_GROUP_A]
                    this.commonData.setPayGroup(this.sPayGroup)
                    this.commonData = msf8p1Obj.initMSF8P1Obj(this.commonData,true)
                    this.msf8P1Rec = msf8p1Obj.populateMsf8p1(m82xRec, new MSF8P1Rec())
                    this.commonData.setPeriodEndDate(this.msf8P1Rec.getPerEndDt())
                    this.commonData.setPeriodStartDate(this.msf8P1Rec.getPerStartDt())
                    this.m8pRec.setMsf8p1Rec(this.msf8P1Rec)

                }

                if(sameGroupOfRecord){
                    //do something for same employee
                    // Each Employee must only has 1 record A
                    this.errorString.add(new String("Unexpected Record A Detected at Line:"+iLineNo + " - " + inputString))
                }else{
                    if (!this.m8pRec.getListMsf8p4Rec().isEmpty()){
                        this.commonMethd.info("getListMsf8p4Rec not Empty",getClass())
                        writeEmployeeRecord()
                    }
                    msf8p4Obj = new AABPAYMSF8P4Rec();
                    this.sEmpId = m82xRec[m82x.M82X_EMPLOYEE_ID]
                    this.sPaySlipType = m82xRec[m82x.M82X_PAYSLIP_TYPE]
                    this.commonData.setEmpId(this.sEmpId)
                    this.commonData = msf8p4Obj.initMSF8P4Obj(this.commonData,true)
                    this.msf8p4Rec = msf8p4Obj.populateMsf8p4(m82xRec,new MSF8P4Rec())
                    this.m8pRec.addMsf8p4Rec(this.msf8p4Rec)
                    this.iMsf8p4Index = this.m8pRec.getCurrIndexMsf8p4Rec()
                    msf8p4Obj.listMessages8PCRec.each{
                        this.m8pRec.addMsf8pCRec(it)
                    }

                }

                break

//              No need to extract B record as all messages will be retrieved directly via MSF839            
//              case m82x.B_RECORD:
//                this.commonMethd.info("populateFields - Processing B Record", getClass())
//                if (!sameGroupOfRecord){
//                    // report error
//                    //Record B must have the same header record with the previous A record
//                    this.errorString.add(new String("Unexpected Record B Detected at Line:"+iLineNo + " - " + inputString))
//                    break
//                }
//                AABPAYMSF8PCRec msf8pCObj = new AABPAYMSF8PCRec()
//                this.commonData = msf8pCObj.initMSF8PCObj(this.commonData)
//                MSF8PCRec msf8pCrec = msf8pCObj.populateMsf8pC(m82xRec, new MSF8PCRec())
//                this.m8pRec.addMsf8pCRec(msf8pCrec)
//
//                break

            case m82x.D_RECORD:
                this.commonMethd.info("populateFields - Processing D Record", getClass())
                if (!sameGroupOfRecord){
                    // report error
                    //Record D must have the same header record with the previous A record
                    this.errorString.add(new String("Unexpected Record D Detected at Line:"+iLineNo + " - " + inputString))

                    break
                }
                AABPAYMSF8PDRec msf8pDObj = new AABPAYMSF8PDRec()
                this.commonData = msf8pDObj.initMSF8PDObj(this.commonData)
                MSF8PDRec msf8pDrec = msf8pDObj.populateMsf8pD(m82xRec, new MSF8PDRec())
                this.m8pRec.addMsf8pDRec(msf8pDrec)
                break

            case m82x.F_RECORD:
                this.commonMethd.info("populateFields - Processing F Record", getClass())
                if (!sameGroupOfRecord){
                    // report error
                    //Record F must have the same header record with the previous A record
                    this.errorString.add(new String("Unexpected Record F Detected at Line:"+iLineNo + " - " + inputString))

                    break
                }

                AABPAYMSF8PBRec msf8pBObj = new AABPAYMSF8PBRec()
                this.commonData = msf8pBObj.initMSF8PBObj(this.commonData)
                MSF8PBRec msf8pBRec = msf8pBObj.populateMsf8pB(m82xRec, new MSF8PBRec())
                this.m8pRec.addMsf8pBRec(msf8pBRec)
                break

            case m82x.H_RECORD:
                this.commonMethd.info("populateFields - Processing H Record", getClass())

                if (!sameGroupOfRecord){
                    // report error
                    //Record H must have the same header record with the previous A record
                    this.errorString.add(new String("Unexpected Record H Detected at Line:"+iLineNo + " - " + inputString))

                    break
                }
                msf8p1Obj = new AABPAYMSF8P1Rec()
                this.commonData = msf8p1Obj.initMSF8P1Obj(this.commonData, false)
                this.msf8P1Rec = msf8p1Obj.populateMsf8p1(m82xRec, this.msf8P1Rec)
                this.m8pRec.setMsf8p1Rec(this.msf8P1Rec)
                msf8p4Obj = new AABPAYMSF8P4Rec()
                this.commonData = msf8p4Obj.initMSF8P4Obj(this.commonData, false)
                this.msf8p4Rec = msf8p4Obj.populateMsf8p4(m82xRec, this.msf8p4Rec)
                this.m8pRec.edtListMsf8p4Rec(this.iMsf8p4Index, this.msf8p4Rec)
                break

            case m82x.J_RECORD:
            // the L_RECORD will be process as the same with J_RECORD thus it will have the same logic with J_RECORD
            // potential it can be move into one method.

                this.commonMethd.info("populateFields - Processing J Record", getClass())

                if (!sameGroupOfRecord){
                    // report error
                    //Record J must have the same header record with the previous A record
                    this.errorString.add(new String("Unexpected Record J Detected at Line:"+iLineNo + " - " + inputString))

                    break
                }

                ArrayList <MSF8P2Rec> lMsf8p2Rec = new ArrayList <MSF8P2Rec>()
                String sPayAdvEanUuid = null
                lMsf8p2Rec = this.m8pRec.getListMsf8p2Rec()
                lMsf8p2Rec.each {MSF8P2Rec each_msf8p2rec ->
                    if (m82xRec[m82x.M82X_EARN_CODE_J].equals(each_msf8p2rec.getEarnCode())){
                        MSF8P2Key msf8p2key = each_msf8p2rec.getPrimaryKey()
                        sPayAdvEanUuid = msf8p2key.getPayAdvEanUuid()
                        commonMethd.info("EarnCode:" +m82xRec[m82x.M82X_EARN_CODE_J] + "Exists with PayAdvEanUuid:" + sPayAdvEanUuid,getClass())
                    }
                }

                if (sPayAdvEanUuid != null){
                    this.commonData.setPayAdvEanUuid(sPayAdvEanUuid)
                }else{
                    AABPAYMSF8P2Rec msf8p2Obj = new AABPAYMSF8P2Rec()
                    this.commonData = msf8p2Obj.initMSF8P2Obj(this.commonData)
                    sPayAdvEanUuid = this.commonData.getPayAdvEanUuid()
                    MSF8P2Rec msf8p2Rec =msf8p2Obj.populateMsf8p2(m82xRec,new MSF8P2Rec())
                    this.m8pRec.addMsf8p2Rec(msf8p2Rec)
                }


                ArrayList <MSF8P5Rec> lMsf8p5Rec = new ArrayList <MSF8P5Rec>()
                boolean bPayYtdEarnUuid = false
                String sPayEmpUuid = this.commonData.getPayAdvEmpUuid()
                String sMst82xEanCode = m82xRec[m82x.M82X_EARN_CODE_J]

                lMsf8p5Rec = this.m8pRec.getListMsf8p5Rec()
                lMsf8p5Rec.each{MSF8P5Rec each_msf8p5Rec ->

                    if ((sMst82xEanCode.equals(each_msf8p5Rec.getEarnCode()))
                    &&
                    (sPayAdvEanUuid.equals(each_msf8p5Rec.getPayAdvEanUuid()))
                    &&
                    (sPayEmpUuid.equals(each_msf8p5Rec.getPayAdvEmpUuid()))
                    ){
                        bPayYtdEarnUuid = true
                    }

                }

                if (!bPayYtdEarnUuid){
                    AABPAYMSF8P5Rec msf8p5Obj = new AABPAYMSF8P5Rec()
                    this.commonData = msf8p5Obj.initMSF8P5Obj(this.commonData)
                    MSF8P5Rec msf8p5Rec = msf8p5Obj.populateMsf8p5(m82xRec,new MSF8P5Rec())
                    this.m8pRec.addMsf8p5Rec(msf8p5Rec)
                }

                AABPAYMSF8P9Rec msf8p9Obj = new AABPAYMSF8P9Rec()
                this.commonData = msf8p9Obj.initMSF8P9Obj(this.commonData)
                MSF8P9Rec msf8p9Rec = msf8p9Obj.populateMsf8p9(m82xRec, new MSF8P9Rec())
                this.m8pRec.addMsf8p9Rec(msf8p9Rec)

                break

            case m82x.L_RECORD:
            // the L_RECORD will be process as the same with J_RECORD thus it will have the same logic with J_RECORD
            // potential it can be move into one method.

                this.commonMethd.info("populateFields - Processing L Record", getClass())

                if (!sameGroupOfRecord){
                    // report error
                    //Record J must have the same header record with the previous A record
                    this.errorString.add(new String("Unexpected Record L Detected at Line:"+iLineNo + " - " + inputString))

                    break
                }

                ArrayList <MSF8P2Rec> lMsf8p2Rec = new ArrayList <MSF8P2Rec>()
                String sPayAdvEanUuid = null
                lMsf8p2Rec = this.m8pRec.getListMsf8p2Rec()
                lMsf8p2Rec.each {MSF8P2Rec each_msf8p2rec ->
                    if (m82xRec[m82x.M82X_ALL_CODE_L].equals(each_msf8p2rec.getEarnCode())){
                        MSF8P2Key msf8p2key = each_msf8p2rec.getPrimaryKey()
                        sPayAdvEanUuid = msf8p2key.getPayAdvEanUuid()
                        commonMethd.info("EarnCode:" +m82xRec[m82x.M82X_ALL_CODE_L] + "Exists with PayAdvEanUuid:" + sPayAdvEanUuid,getClass())
                    }
                }

                if (sPayAdvEanUuid != null){
                    this.commonData.setPayAdvEanUuid(sPayAdvEanUuid)
                }else{
                    AABPAYMSF8P2Rec msf8p2Obj = new AABPAYMSF8P2Rec()
                    this.commonData = msf8p2Obj.initMSF8P2Obj(this.commonData)
                    sPayAdvEanUuid = this.commonData.getPayAdvEanUuid()
                    MSF8P2Rec msf8p2Rec =msf8p2Obj.populateMsf8p2(m82xRec,new MSF8P2Rec())
                    this.m8pRec.addMsf8p2Rec(msf8p2Rec)
                }


                ArrayList <MSF8P5Rec> lMsf8p5Rec = new ArrayList <MSF8P5Rec>()
                boolean bPayYtdEarnUuid = false
                String sPayEmpUuid = this.commonData.getPayAdvEmpUuid()
                String sMst82xEanCode = m82xRec[m82x.M82X_ALL_CODE_L]

                lMsf8p5Rec = this.m8pRec.getListMsf8p5Rec()
                lMsf8p5Rec.each{MSF8P5Rec each_msf8p5Rec ->

                    if ((sMst82xEanCode.equals(each_msf8p5Rec.getEarnCode()))
                    &&
                    (sPayAdvEanUuid.equals(each_msf8p5Rec.getPayAdvEanUuid()))
                    &&
                    (sPayEmpUuid.equals(each_msf8p5Rec.getPayAdvEmpUuid()))
                    ){
                        bPayYtdEarnUuid = true
                    }

                }

                if (!bPayYtdEarnUuid){
                    AABPAYMSF8P5Rec msf8p5Obj = new AABPAYMSF8P5Rec()
                    this.commonData = msf8p5Obj.initMSF8P5Obj(this.commonData)
                    MSF8P5Rec msf8p5Rec = msf8p5Obj.populateMsf8p5(m82xRec,new MSF8P5Rec())
                    this.m8pRec.addMsf8p5Rec(msf8p5Rec)
                }

                AABPAYMSF8P9Rec msf8p9Obj = new AABPAYMSF8P9Rec()
                this.commonData = msf8p9Obj.initMSF8P9Obj(this.commonData)
                MSF8P9Rec msf8p9Rec = msf8p9Obj.populateMsf8p9(m82xRec, new MSF8P9Rec())
                this.m8pRec.addMsf8p9Rec(msf8p9Rec)

                break;


            case m82x.N_RECORD:
                this.commonMethd.info("populateFields - Processing N Record", getClass())

                if (!sameGroupOfRecord){
                    //Record N must have the same header record with the previous A record
                    this.errorString.add(new String("Unexpected Record N Detected at Line:"+iLineNo + " - " + inputString))
                    break
                }

                ArrayList <MSF8P3Rec> lMsf8p3Rec = new ArrayList <MSF8P3Rec>()
                String sPayAdvDedUuid = null
                lMsf8p3Rec = this.m8pRec.getListMsf8p3Rec()
                lMsf8p3Rec.each {MSF8P3Rec each_msf8p3rec ->
                    if (m82xRec[m82x.M82X_DED_CODE_N].equals(each_msf8p3rec.getDednCode())){
                        MSF8P3Key msf8p3key = each_msf8p3rec.getPrimaryKey()
                        sPayAdvDedUuid = msf8p3key.getPayAdvDedUuid()
                        commonMethd.info("DedCode:" +m82xRec[m82x.M82X_DED_CODE_N] + " Exists with PayAdvDedUuid:" + sPayAdvDedUuid,getClass()  )
                    }
                }

                if (sPayAdvDedUuid != null){
                    this.commonData.setPayAdvDedUuid(sPayAdvDedUuid)
                }else{
                    AABPAYMSF8P3Rec msf8p3Obj = new AABPAYMSF8P3Rec()
                    this.commonData = msf8p3Obj.initMSF8P3Obj(this.commonData)
                    sPayAdvDedUuid = this.commonData.getPayAdvDedUuid()
                    MSF8P3Rec msf8p3Rec = msf8p3Obj.populateMsf8p3(m82xRec, new MSF8P3Rec())
                    this.m8pRec.addMsf8p3Rec(msf8p3Rec)
                }

                ArrayList <MSF8P6Rec> lMsf8p6Rec = new ArrayList <MSF8P6Rec>()
                boolean bPayYtdDednUuid = false
                String sAdvPayEmpUuid = this.commonData.getPayAdvEmpUuid()
                String sMst82xDedCode = m82xRec[m82x.M82X_DED_CODE_N]

                lMsf8p6Rec = this.m8pRec.getListMsf8p6Rec()
                lMsf8p6Rec.each{MSF8P6Rec each_msf8p6Rec ->

                    if ((sMst82xDedCode.equals(each_msf8p6Rec.getDednCode()))
                    &&
                    (sPayAdvDedUuid.equals(each_msf8p6Rec.getPayAdvDedUuid()))
                    &&
                    (sAdvPayEmpUuid.equals(each_msf8p6Rec.getPayAdvEmpUuid()))
                    ){
                        bPayYtdDednUuid = true
                    }
                }

                if (!bPayYtdDednUuid){
                    AABPAYMSF8P6Rec msf8p6Obj = new AABPAYMSF8P6Rec()
                    this.commonData = msf8p6Obj.initMSF8P6Obj(this.commonData)
                    MSF8P6Rec msf8p6Rec = msf8p6Obj.populateMsf8p6(m82xRec,new MSF8P6Rec())
                    this.m8pRec.addMsf8p6Rec(msf8p6Rec)
                }

                AABPAYMSF8PARec msf8pAObj = new AABPAYMSF8PARec()
                this.commonData = msf8pAObj.initMSF8PAObj(this.commonData)
                MSF8PARec msf8pARec = msf8pAObj.populateMsf8pA(m82xRec, new MSF8PARec())
                this.m8pRec.addMsf8pARec(msf8pARec)

                break
            default:
                break
        }

        this.commonMethd.info("Finish - populateFields", getClass())

    }

    public void writeToDatabase(){
        this.commonMethd.info("Writing to Database...", getClass())

        MSF8P1Rec msf8p1rec = this.m8pRec.getMsf8p1Rec()
        ArrayList<MSF8P2Rec> listMsf8p2Rec = this.m8pRec.getListMsf8p2Rec()
        ArrayList<MSF8P3Rec> listMsf8p3Rec = this.m8pRec.getListMsf8p3Rec()

        Integer iMSF8P1 = this.msfCounter.getiMSF8P1()
        Integer iMSF8P2 = this.msfCounter.getiMSF8P2()
        Integer iMSF8P3 = this.msfCounter.getiMSF8P3()

        EDOIWrapper edoi = this.commonData.getSessionBinding().getVariable("edoi");

        MSF8P1Key msf8p1Key = msf8p1rec.getPrimaryKey()

        if (msf8p1Key.getPayAdvHdrUuid().length()>0){
            this.commonMethd.info("Writing MSF8P1", getClass())
            edoi.create(msf8p1rec)
            iMSF8P1++
        }

        if (listMsf8p2Rec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8P2", getClass())
            listMsf8p2Rec.each {MSF8P2Rec msf8p2Rec -> iMSF8P2++ }
            edoiCreate(edoi,listMsf8p2Rec)

        }

        if (listMsf8p3Rec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8P3", getClass())
            listMsf8p3Rec.each {MSF8P3Rec msf8p3Rec -> iMSF8P3++ }
            edoiCreate(edoi,listMsf8p3Rec)

        }

        writeEmployeeRecord()

        //once write reset the object
        this.m8pRec.initMsf8PSeriesRec()

        this.msfCounter.setiMSF8P1(iMSF8P1)
        this.msfCounter.setiMSF8P2(iMSF8P2)
        this.msfCounter.setiMSF8P3(iMSF8P3)

    }

    private void writeEmployeeRecord(){
        EDOIWrapper edoi = this.commonData.getSessionBinding().getVariable("edoi");
        ArrayList<MSF8P4Rec> listMsf8p4Rec = this.m8pRec.getListMsf8p4Rec()
        ArrayList<MSF8P5Rec> listMsf8p5Rec = this.m8pRec.getListMsf8p5Rec()
        ArrayList<MSF8P6Rec> listMsf8p6Rec = this.m8pRec.getListMsf8p6Rec()
        ArrayList<MSF8P7Rec> listMsf8p7Rec = this.m8pRec.getListMsf8p7Rec()
        ArrayList<MSF8P8Rec> listMsf8p8Rec = this.m8pRec.getListMsf8p8Rec()
        ArrayList<MSF8P9Rec> listMsf8p9Rec = this.m8pRec.getListMsf8p9Rec()
        ArrayList<MSF8PARec> listMsf8pARec = this.m8pRec.getListMsf8pARec()
        ArrayList<MSF8PBRec> listMsf8pBRec = this.m8pRec.getListMsf8pBRec()
        ArrayList<MSF8PCRec> listMsf8pCRec = this.m8pRec.getListMsf8pCRec()
        ArrayList<MSF8PDRec> listMsf8pDRec = this.m8pRec.getListMsf8pDRec()
        ArrayList<MSF8PERec> listMsf8pERec = this.m8pRec.getListMsf8pERec()
        Integer iMSF8P4 = this.msfCounter.getiMSF8P4()
        Integer iMSF8P5 = this.msfCounter.getiMSF8P5()
        Integer iMSF8P6 = this.msfCounter.getiMSF8P6()
        Integer iMSF8P7 = this.msfCounter.getiMSF8P7()
        Integer iMSF8P8 = this.msfCounter.getiMSF8P8()
        Integer iMSF8P9 = this.msfCounter.getiMSF8P9()
        Integer iMSF8PA = this.msfCounter.getiMSF8PA()
        Integer iMSF8PB = this.msfCounter.getiMSF8PB()
        Integer iMSF8PC = this.msfCounter.getiMSF8PC()
        Integer iMSF8PD = this.msfCounter.getiMSF8PD()
        Integer iMSF8PE = this.msfCounter.getiMSF8PE()


        if (listMsf8p4Rec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8P4", getClass())
            listMsf8p4Rec.each {MSF8P4Rec msf8p4Rec -> iMSF8P4++ }
            edoiCreate(edoi,listMsf8p4Rec)

        }

        if (listMsf8p5Rec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8P5", getClass())
            listMsf8p5Rec.each {MSF8P5Rec msf8p5Rec -> iMSF8P5++ }
            edoiCreate(edoi,listMsf8p5Rec)
        }

        if (listMsf8p6Rec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8P6", getClass())
            listMsf8p6Rec.each {MSF8P6Rec msf8p6Rec -> iMSF8P6++ }
            edoiCreate(edoi,listMsf8p6Rec)

        }

        if (listMsf8p7Rec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8P7", getClass())
            listMsf8p7Rec.each {MSF8P7Rec msf8p7Rec -> iMSF8P7++ }
            edoiCreate(edoi,listMsf8p7Rec)
        }

        if (listMsf8p8Rec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8P8", getClass())
            listMsf8p8Rec.each {MSF8P8Rec msf8p8Rec -> iMSF8P8++ }
            edoiCreate(edoi,listMsf8p8Rec)
        }

        if (listMsf8p9Rec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8P9", getClass())
            listMsf8p9Rec.each {MSF8P9Rec msf8p9Rec -> iMSF8P9++ }
            edoiCreate(edoi,listMsf8p9Rec)

        }

        if (listMsf8pARec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8PA", getClass())
            listMsf8pARec.each {MSF8PARec msf8pARec -> iMSF8PA++ }
            edoiCreate(edoi,listMsf8pARec)
        }

        if (listMsf8pBRec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8PB", getClass())
            listMsf8pBRec.each {MSF8PBRec msf8pBRec -> iMSF8PB++ }
            edoiCreate(edoi,listMsf8pBRec)
        }

        if (listMsf8pCRec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8PC", getClass())
            listMsf8pCRec.each {MSF8PCRec msf8pCRec -> iMSF8PC++ }
            edoiCreate(edoi,listMsf8pCRec)

        }

        if (listMsf8pDRec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8PD", getClass())
            listMsf8pDRec.each {MSF8PDRec msf8pDRec -> iMSF8PD++ }
            edoiCreate(edoi,listMsf8pDRec)
        }

        if (listMsf8pERec.isEmpty()){
            //do something
        }else{
            this.commonMethd.info("Writing MSF8PE", getClass())
            listMsf8pERec.each {MSF8PERec msf8pERec -> iMSF8PE++ }
            edoiCreate(edoi,listMsf8pERec)

        }

        this.msfCounter.setiMSF8P4(iMSF8P4)
        this.msfCounter.setiMSF8P5(iMSF8P5)
        this.msfCounter.setiMSF8P6(iMSF8P6)
        this.msfCounter.setiMSF8P7(iMSF8P7)
        this.msfCounter.setiMSF8P8(iMSF8P8)
        this.msfCounter.setiMSF8P9(iMSF8P9)
        this.msfCounter.setiMSF8PA(iMSF8PA)
        this.msfCounter.setiMSF8PB(iMSF8PB)
        this.msfCounter.setiMSF8PC(iMSF8PC)
        this.msfCounter.setiMSF8PD(iMSF8PD)
        this.msfCounter.setiMSF8PE(iMSF8PE)
        this.m8pRec.initEmpRecord()

    }

    private void edoiCreate(EDOIWrapper edoi, List  <Object> paramRec){
        edoi.createAll(paramRec)
    }
}

public class AABPAYMSF8P1Rec{
    private String s8P1UUID

    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8P1Obj(AABPAYM8pCommonData inpCommonData,boolean createUUID){
        this.commonData = inpCommonData
        if (createUUID){
            this.s8P1UUID = UUID.randomUUID()
            this.s8P1UUID = this.s8P1UUID.replaceAll("-","").toUpperCase()
            this.commonData.setPayAdvHdrUuid(this.s8P1UUID)
        }
        return this.commonData
    }

    public MSF8P1Rec populateMsf8p1(ArrayList <String> mst82xRec, MSF8P1Rec msf8p1rec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8P1Rec m8p1rec = msf8p1rec

        this.commonMethd.info("MSF8P1 Uuid:" + this.s8P1UUID ,getClass() )
        this.commonMethd.initAABPAYCommonMethod(this.commonData)

        m8p1rec.setLastModDate(this.commonData.getLastModDate())
        m8p1rec.setLastModTime(this.commonData.getLastModTime())
        m8p1rec.setLastModUser(this.commonData.getLastModUser())

        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.A_RECORD)){
            this.commonMethd.info("Record Type:A",getClass() )
            m8p1rec.setPrimaryKey(new MSF8P1Key(this.s8P1UUID))
            m8p1rec.setPayRunNo(mst82xRec[m82x.M82X_RUN_NO])

            m8p1rec.setPayGroup(mst82xRec[m82x.M82X_PAY_GROUP_A])
            m8p1rec.setPayFrq(mst82xRec[m82x.M82X_PAY_FREQ_A])
            m8p1rec.setPerEndDt(mst82xRec[m82x.M82X_PRV_END_DT_PG_A])
            m8p1rec.setPerStartDt(mst82xRec[m82x.M82X_PRV_STR_DT_PG_A])
            m8p1rec.setPayRunType(this.commonData.getPayRunType())
            //Values from MSF801_PG_801
            MSF801_PG_801Rec msf801PGRec = this.commonMethd.getMSF801_PG(this.commonData)

            m8p1rec.setPayGroupName(msf801PGRec.getTnamePg())
            m8p1rec.setPgAdmin(msf801PGRec.getAdminPg())
            m8p1rec.setPgContact(msf801PGRec.getContactNamePg())
            m8p1rec.setPgContactPhone(msf801PGRec.getContactPhonePg())

            //Values from MSF000_DC0004
            MSF000_DC0004Rec msf000_dc0004Rec = this.commonMethd.getMSF000_DC0004(this.commonData)
            m8p1rec.setEmployerAddr_1(msf000_dc0004Rec.getDstrctAddr_1())
            m8p1rec.setEmployerAddr_2(msf000_dc0004Rec.getDstrctAddr_2())
            m8p1rec.setEmployerAddr_3(msf000_dc0004Rec.getDstrctAddr_3())
            m8p1rec.setEmployerState(msf000_dc0004Rec.getDstrctState())
            m8p1rec.setEmployerZipcode(msf000_dc0004Rec.getDstrctZipCode())

            //Values from Commarea
            CommAreaScriptWrapper commarea
            commarea = this.commonData.getSessionBinding().getVariable("commarea")
            m8p1rec.setCurrencyCde(commarea.LocalCurrency)


        }

        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.H_RECORD)){
            this.commonMethd.info("Record Type:H" ,getClass() )
            m8p1rec.setPayDate(mst82xRec[m82x.M82X_PAY_DATE_H])
            m8p1rec.setEmployerId(mst82xRec[m82x.M82X_EMPLOYER_ABN_H])
            m8p1rec.setEmployerName(mst82xRec[m82x.M82X_EMPLOYER_NAME_H])
        }


        return m8p1rec
    }


}

public class AABPAYMSF8P2Rec{
    private String s8P2UUID
    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8P2Obj(AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8P2UUID = UUID.randomUUID()
        this.s8P2UUID = this.s8P2UUID.replaceAll("-","").toUpperCase()
        this.commonData.setPayAdvEanUuid(this.s8P2UUID)
        return this.commonData
    }

    public MSF8P2Rec populateMsf8p2(ArrayList <String> mst82xRec, MSF8P2Rec msf8p2rec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8P2Rec m8p2rec = msf8p2rec
        this.commonMethd.initAABPAYCommonMethod(this.commonData)

        MSF801_A_801Rec msf801aRec = new MSF801_A_801Rec()

        // Based on MST82Z J Record
        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.J_RECORD)){
            this.commonMethd.info("J record - MSF8P2 Uuid:" + this.s8P2UUID + " EarnCode:" + mst82xRec[m82x.M82X_EARN_CODE_J],getClass())

            m8p2rec.setPrimaryKey(new MSF8P2Key(s8P2UUID))
            m8p2rec.setPayAdvHdrUuid(this.commonData.getPayAdvHdrUuid())
            m8p2rec.setLastModDate(this.commonData.getLastModDate())
            m8p2rec.setLastModTime(this.commonData.getLastModTime())
            m8p2rec.setLastModUser(this.commonData.getLastModUser())

            m8p2rec.setEarnCode(mst82xRec[m82x.M82X_EARN_CODE_J])
            m8p2rec.setEarnCodeDesc(mst82xRec[m82x.M82X_EARN_DESC_J])
            m8p2rec.setEarnCodeShDesc(mst82xRec[m82x.M82X_EARN_SH_DESC_J])
            m8p2rec.setPayslipBox(mst82xRec[m82x.M82X_PAYSLIP_BOX_IND_J])
            m8p2rec.setPayMethod(mst82xRec[m82x.M82X_PAY_METHOD_J])
            m8p2rec.setEarnClass(mst82xRec[m82x.M82X_EARN_CLASS_J])

            //Value from MSF80A
            msf801aRec = this.commonMethd.getMSF801ARec(this.commonData, mst82xRec[m82x.M82X_EARN_CODE_J],false)

            if (msf801aRec.getTnameA().trim() == ""){
                msf801aRec = this.commonMethd.getMSF801ARec(this.commonData, mst82xRec[m82x.M82X_EARN_CODE_J],true)
            }
        }

        // Based on MST82Z L Record
        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.L_RECORD)){

            mst82xRec.each{
                this.commonMethd.info(it,getClass())
            }
            this.commonMethd.info("L record - MSF8P2 Uuid:" + this.s8P2UUID + " EarnCode:" + mst82xRec[m82x.M82X_ALL_CODE_L],getClass())

            m8p2rec.setPrimaryKey(new MSF8P2Key(s8P2UUID))
            m8p2rec.setPayAdvHdrUuid(this.commonData.getPayAdvHdrUuid())
            m8p2rec.setLastModDate(this.commonData.getLastModDate())
            m8p2rec.setLastModTime(this.commonData.getLastModTime())
            m8p2rec.setLastModUser(this.commonData.getLastModUser())

            m8p2rec.setEarnCode(mst82xRec[m82x.M82X_ALL_CODE_L])
            m8p2rec.setEarnCodeDesc(mst82xRec[m82x.M82X_ALL_DESC_L])
            m8p2rec.setEarnCodeShDesc(mst82xRec[m82x.M82X_ALL_SHORT_DESC_L])
            m8p2rec.setPayslipBox(mst82xRec[m82x.M82X_PAYSLIP_BOX_IND_L])
            m8p2rec.setPayMethod(mst82xRec[m82x.M82X_PAY_METHOD_L])


            //Value from MSF80A
            msf801aRec = this.commonMethd.getMSF801ARec(this.commonData, mst82xRec[m82x.M82X_ALL_CODE_L],false)
            if (msf801aRec.getTnameA().trim() == ""){
                msf801aRec = this.commonMethd.getMSF801ARec(this.commonData, mst82xRec[m82x.M82X_ALL_CODE_L],true)
            }

            m8p2rec.setEarnClass(msf801aRec.getEarnClassA())
        }


        m8p2rec.setTaxMethod(msf801aRec.getTaxMethodA())
        m8p2rec.setEarningsType(msf801aRec.getEarnTypeA())
        m8p2rec.setMiscRptFldx1(msf801aRec.getMiscRptFldAx1())
        m8p2rec.setMiscRptFldx2(msf801aRec.getMiscRptFldAx2())
        m8p2rec.setMiscRptFldx3(msf801aRec.getMiscRptFldAx3())
        m8p2rec.setMiscRptFldx4(msf801aRec.getMiscRptFldAx4())
        m8p2rec.setMiscRptFldx5(msf801aRec.getMiscRptFldAx5())

        return  m8p2rec
    }
}

public class AABPAYMSF8P3Rec{
    private String s8P3UUID
    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8P3Obj(AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8P3UUID = UUID.randomUUID()
        this.s8P3UUID = this.s8P3UUID.replaceAll("-","").toUpperCase()
        this.commonData.setPayAdvDedUuid(this.s8P3UUID)
        return this.commonData
    }

    public MSF8P3Rec populateMsf8p3(ArrayList <String> mst82xRec, MSF8P3Rec msf8p3rec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8P3Rec m8p3rec = msf8p3rec

        this.commonMethd.initAABPAYCommonMethod(this.commonData)
        // Based on MST82Z J Record

        this.commonMethd.info("MSF8P3 Uuid:" + this.s8P3UUID + " DedCode:" + mst82xRec[m82x.M82X_DED_CODE_N],getClass())

        m8p3rec.setPrimaryKey(new MSF8P3Key(this.s8P3UUID))
        m8p3rec.setPayAdvHdrUuid(this.commonData.getPayAdvHdrUuid())
        m8p3rec.setLastModDate(this.commonData.getLastModDate())
        m8p3rec.setLastModTime(this.commonData.getLastModTime())
        m8p3rec.setLastModUser(this.commonData.getLastModUser())

        m8p3rec.setDednCode(mst82xRec[m82x.M82X_DED_CODE_N])
        m8p3rec.setDednCodeDesc(mst82xRec[m82x.M82X_DED_LONG_DESC_N])
        m8p3rec.setDedCodeShDesc(mst82xRec[m82x.M82X_DED_DESC_N])
        m8p3rec.setPayslipBox(mst82xRec[m82x.M82X_PAYSLIP_BOX_N])
        m8p3rec.setDednType(mst82xRec[m82x.M82X_DED_TYPE_N])
        m8p3rec.setDednMethod(mst82xRec[m82x.M82X_DED_METHOD_N])
        m8p3rec.setPreTaxInd(mst82xRec[m82x.M82X_PRE_TAX_IND_N])
        m8p3rec.setMiscRptFldx1(mst82xRec[m82x.M82X_MISC_RPT_FLD_01_N])
        m8p3rec.setMiscRptFldx2(mst82xRec[m82x.M82X_MISC_RPT_FLD_02_N])
        m8p3rec.setMiscRptFldx3(mst82xRec[m82x.M82X_MISC_RPT_FLD_03_N])
        m8p3rec.setMiscRptFldx4(mst82xRec[m82x.M82X_MISC_RPT_FLD_04_N])
        m8p3rec.setMiscRptFldx5(mst82xRec[m82x.M82X_MISC_RPT_FLD_05_N])

        //GetSupplierNo
        String sSupplierNo = ""
        sSupplierNo =  this.commonMethd.getSupplierNo(commonData, mst82xRec[m82x.M82X_DED_CODE_N])

        if (sSupplierNo.trim() != ""){
            m8p3rec.setSupplierNo(sSupplierNo)

            if (!m8p3rec.getSupplierNo().trim().empty){

                MSF200Rec msf200rec = this.commonMethd.getMSF200Rec(commonData, m8p3rec.getSupplierNo())
                m8p3rec.setSupplierName(msf200rec.getSupplierName())
            }

        }

        return  m8p3rec
    }
}

public class AABPAYMSF8P4Rec{
    private String s8P4UUID
    private EDOIWrapper edoi;
    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();
    public List <MSF8PCRec> listMessages8PCRec = new ArrayList <MSF8PCRec>()

    public AABPAYM8pCommonData initMSF8P4Obj(AABPAYM8pCommonData inpCommonData, boolean createUUID){
        this.commonData = inpCommonData
        if (createUUID){
            this.s8P4UUID = UUID.randomUUID()
            this.s8P4UUID = this.s8P4UUID.replaceAll("-","").toUpperCase()
            this.commonData.setPayAdvEmpUuid(this.s8P4UUID)

        }
        this.edoi = this.commonData.getSessionBinding().getVariable("edoi");
        return this.commonData
    }

    public MSF8P4Rec populateMsf8p4(ArrayList <String> mst82xRec, MSF8P4Rec msf8p4rec){
        String AWARD_MESSAGE_TYPE = "AW"
        String EMPLOYEE_MESSAGE_TYPE = "EM"
        String PAY_GROUP_MESSAGE_TYPE = "PG"
        String PHYSICAL_LOC_MESSAGE_TYPE = "PH"
        String PAY_LOCATION_MESSAGE_TYPE = "PL"
        String UNION_MESSAGE_TYPE = "UN"
        
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8P4Rec m8p4rec = msf8p4rec
        this.commonMethd.info("MSF8P4 Uuid:" + this.s8P4UUID + " EmpId:" + this.commonData.getEmpId(),getClass())
        this.commonMethd.initAABPAYCommonMethod(this.commonData)
        // From MST82Z
        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.A_RECORD)){

            m8p4rec.setPrimaryKey(new MSF8P4Key(this.s8P4UUID))
            m8p4rec.setPayAdvHdrUuid(this.commonData.getPayAdvHdrUuid())
            m8p4rec.setEmployeeId(mst82xRec[m82x.M82X_EMPLOYEE_ID])
            m8p4rec.setEnvelopeType(mst82xRec[m82x.M82X_PAYSLIP_TYPE])
            m8p4rec.setSurname(mst82xRec[m82x.M82X_SURNAME])
            m8p4rec.setFirstName(mst82xRec[m82x.M82X_FIRST_NAME])
            m8p4rec.setLastModDate(this.commonData.getLastModDate())
            m8p4rec.setLastModTime(this.commonData.getLastModTime())
            m8p4rec.setLastModUser(this.commonData.getLastModUser())


            m8p4rec.setSecondName(mst82xRec[m82x.M82X_SECOND_NAME_A])
            m8p4rec.setPostAddress_1(mst82xRec[m82x.M82X_ADDRESS_1_A])
            m8p4rec.setPostAddress_2(mst82xRec[m82x.M82X_ADDRESS_2_A])
            m8p4rec.setPostAddress_3(mst82xRec[m82x.M82X_ADDRESS_3_A])
            m8p4rec.setPostState(mst82xRec[m82x.M82X_POST_STATE_A])
            m8p4rec.setPostZipcode(mst82xRec[m82x.M82X_POST_CODE_A])
            m8p4rec.setPositionId(mst82xRec[m82x.M82X_POSITION_ID_A])
            m8p4rec.setPosTitle(mst82xRec[m82x.M82X_POSITION_TITLE_A])
            m8p4rec.setEmployeeClass(mst82xRec[m82x.M82X_EMPLOYEE_CLASS_A])
            m8p4rec.setRateRefCode(mst82xRec[m82x.M82X_RATE_REF_A])
            m8p4rec.setRateRefDesc(mst82xRec[m82x.M82X_RATE_REF_DESC_A].trim())
            m8p4rec.setHourlyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_HRLY_RATE_A]))
            m8p4rec.setDailyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_DAILY_RATE_A]))
            m8p4rec.setWeeklyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_WEEKLY_RATE_A]))
            m8p4rec.setPeriodRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_PRD_RATE_A]))
            m8p4rec.setAnnualRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_ANN_RATE_A]))
            m8p4rec.setWorkGroup(mst82xRec[m82x.M82X_WORK_GROUP_A])
            m8p4rec.setWorkGrpDesc(mst82xRec[m82x.M82X_WORK_GROUP_DESC_A].trim())
            m8p4rec.setPhysicalLoc(mst82xRec[m82x.M82X_PHYSICAL_LOC])
            m8p4rec.setPhysLocDesc(mst82xRec[m82x.M82X_PHYS_LOCN_DESC_A].trim())
            m8p4rec.setPayLocation(mst82xRec[m82x.M82X_PAY_LOCATION])
            m8p4rec.setPayLocnDesc(mst82xRec[m82x.M82X_PAY_LOC_DESC_A].trim())
            m8p4rec.setPrimRptCdex1(mst82xRec[m82x.M82X_PRC_1_A])
            m8p4rec.setPrimRptCdex2(mst82xRec[m82x.M82X_PRC_2_A])
            m8p4rec.setPrimRptCdex3(mst82xRec[m82x.M82X_PRC_3_A])
            m8p4rec.setPrimRptCdex4(mst82xRec[m82x.M82X_PRC_4_A])
            m8p4rec.setPrimRptCdex5(mst82xRec[m82x.M82X_PRC_5_A])
            m8p4rec.setPrimRptCdex6(mst82xRec[m82x.M82X_PRC_6_A])
            m8p4rec.setPrimRptCdex7(mst82xRec[m82x.M82X_PRC_7_A])
            m8p4rec.setPrimRptCdex8(mst82xRec[m82x.M82X_PRC_8_A])
            m8p4rec.setPrimRptCdex9(mst82xRec[m82x.M82X_PRC_9_A])
            m8p4rec.setPrimRptCdex10(mst82xRec[m82x.M82X_PRC_10_A])
            m8p4rec.setHireDate(mst82xRec[m82x.M82X_HIRE_DATE])

            MSF723Rec msf723Rec = this.commonMethd.getMSF723Rec(this.commonData, mst82xRec[m82x.M82X_WORK_GROUP_A])
            MSF010Rec msf010Rec = this.commonMethd.getMSF010Rec("CREW", msf723Rec.getCrew())
            MSF760Rec msf760Rec = this.commonMethd.getMSF760Rec(this.commonData)
            m8p4rec.setCrew(msf723Rec.getCrew())
            m8p4rec.setCrewDesc(msf010Rec.getTableDesc())
            m8p4rec.setEmpStatus(msf760Rec.getEmpStatus())
            m8p4rec.setUnionCode(msf760Rec.getUnionCode())

            msf010Rec = this.commonMethd.getMSF010Rec("UN", msf760Rec.getUnionCode())
            m8p4rec.setUnionCodeDesc(msf010Rec.getTableDesc())
            m8p4rec.setWorkLocation(mst82xRec[m82x.M82X_WORK_LOCATION])

            msf010Rec = this.commonMethd.getMSF010Rec("WRKL", mst82xRec[m82x.M82X_WORK_LOCATION])
            m8p4rec.setWorkLocnDesc(msf010Rec.getTableDesc())

            MSF820Rec msf820Rec = this.commonMethd.getMSF820Rec(this.commonData)
            MSF801_C0_801Rec msf801C0Rec = this.commonMethd.getMSF801_C0(msf820Rec.getRptAwardCode())
            m8p4rec.setAwdHrsPerWeek(msf801C0Rec.getStdHrsWkC0())
            m8p4rec.setEmpHrsPerWeek(msf820Rec.getContractHours())


            // value from MSF810
            MSF810Rec msf810Rec = this.commonMethd.getMSF810Rec(this.commonData)
            m8p4rec.setPreferredName(msf810Rec.getPrefName())
            m8p4rec.setNameTitle(msf810Rec.getNameTitle())
            m8p4rec.setPslDistribMeth(msf810Rec.getPslDistribMeth())
            m8p4rec.setPostCntry(msf810Rec.getPostCntry())
            m8p4rec.setEmailAddress(msf810Rec.getEmailAddress())
            m8p4rec.setPersonalEmail(msf810Rec.getPersonalEmail())
            m8p4rec.setBarcode(msf810Rec.getBarcode())
            m8p4rec.setSocSecNo(msf810Rec.getSocSecNo())
            m8p4rec.setSsnType(msf810Rec.getSsnType())
            m8p4rec.setSocInsNo(msf810Rec.getSocInsNo())
            m8p4rec.setNatIdCode_1(msf810Rec.getNatIdCode_1())
            m8p4rec.setNatIdType_1(msf810Rec.getNatIdType_1())
            m8p4rec.setNatIdCode_2(msf810Rec.getNatIdCode_1())
            m8p4rec.setNatIdType_2(msf810Rec.getNatIdType_1())
            m8p4rec.setNatIdCode_3(msf810Rec.getNatIdCode_1())
            m8p4rec.setNatIdType_3(msf810Rec.getNatIdType_1())
            m8p4rec.setNatIdCode_4(msf810Rec.getNatIdCode_1())
            m8p4rec.setNatIdType_4(msf810Rec.getNatIdType_1())
            m8p4rec.setNatIdCode_5(msf810Rec.getNatIdCode_1())
            m8p4rec.setNatIdType_5(msf810Rec.getNatIdType_1())
            m8p4rec.setSupplierNo(msf810Rec.getSupplierNo())

            // retrieve messages
            getMessages(msf820Rec.getRptAwardCode(),AWARD_MESSAGE_TYPE,this.commonMethd.getMaxRecordRead())  //AW
            getMessages(m8p4rec.getEmployeeId(),EMPLOYEE_MESSAGE_TYPE,this.commonMethd.getMaxRecordRead())  //EM 
            getMessages(this.commonData.getPayGroup(),PAY_GROUP_MESSAGE_TYPE,this.commonMethd.getMaxRecordRead())//PG
            getMessages(m8p4rec.getPhysicalLoc(),PHYSICAL_LOC_MESSAGE_TYPE,this.commonMethd.getMaxRecordRead()) //PH
            getMessages(m8p4rec.getPayLocation(),PAY_LOCATION_MESSAGE_TYPE,this.commonMethd.getMaxRecordRead()) //PL
            getMessages(msf760Rec.getUnionCode(),UNION_MESSAGE_TYPE,this.commonMethd.getMaxRecordRead()) //UN
            
        }

        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.H_RECORD)){
            m8p4rec.setCompAnnualRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_CUR_COMP_ANN_RATE]))
            m8p4rec.setCompDailyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_CUR_COMP_DAILY_RATE]))
            m8p4rec.setCompHourlyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_CUR_COMP_HRLY_RATE]))
            m8p4rec.setCompPeriodRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_CUR_COMP_PRD_RATE]))
            m8p4rec.setCompWeeklyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_CUR_COMP_WEEKLY_RATE]))
        }

        return m8p4rec

    }

    private void getMessages(String messageCode, String messageType, Integer maxRecordRead){
        MSF8PCRec msf8pcRec = new MSF8PCRec()
        Constraint c1 = MSF839Key.messageCode.equalTo(messageCode)
        Constraint c2 = MSF839Key.messageType.equalTo(messageType)
        Constraint c3 = MSF839Key.startDate.lessThanEqualTo(this.commonData.getPeriodEndDate())
        Constraint c4 = MSF839Rec.endDate.greaterThanEqualTo(this.commonData.getPeriodStartDate())
        Constraint c5 = MSF839Key.seqNumber.equalTo("000")
        def query = new QueryImpl (MSF839Rec.class).and(c1).and(c2).and(c3).and(c4).and(c5)

        this.edoi.search(query,maxRecordRead,{MSF839Rec msf839rec ->
            AABPAYMSF8PCRec msf8pCObj = new AABPAYMSF8PCRec()
            this.commonData = msf8pCObj.initMSF8PCObj(this.commonData)
            MSF8PCRec msf8pCrec = msf8pCObj.populateMsf8pC(msf839rec)
            this.listMessages8PCRec.add(msf8pCrec)
        })
    }
}

public class AABPAYMSF8P5Rec{
    private String s8P5UUID
    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8P5Obj(AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8P5UUID = UUID.randomUUID()
        this.s8P5UUID = this.s8P5UUID.replaceAll("-","").toUpperCase()
        return this.commonData
    }

    public MSF8P5Rec populateMsf8p5 (ArrayList <String> mst82xRec, MSF8P5Rec msf8p5rec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8P5Rec m8p5rec = msf8p5rec
        this.commonMethd.initAABPAYCommonMethod(this.commonData)

        m8p5rec.setPrimaryKey(new MSF8P5Key (this.s8P5UUID))
        m8p5rec.setPayAdvEmpUuid(this.commonData.getPayAdvEmpUuid())
        m8p5rec.setPayAdvEanUuid(this.commonData.getPayAdvEanUuid())

        m8p5rec.setLastModDate(this.commonData.getLastModDate())
        m8p5rec.setLastModTime(this.commonData.getLastModTime())
        m8p5rec.setLastModUser(this.commonData.getLastModUser())

        AABPAY_YTD aabPayYtd = new AABPAY_YTD()
        // Based on MST82Z J Record
        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.J_RECORD)){
            m8p5rec.setEarnCode(mst82xRec[m82x.M82X_EARN_CODE_J])
            aabPayYtd = this.commonMethd.getEMP_YTD(this.commonData, mst82xRec[m82x.M82X_EARN_CODE_J], "A")
        }

        // Based on MST82Z L Record
        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.L_RECORD)){
            m8p5rec.setEarnCode(mst82xRec[m82x.M82X_ALL_CODE_L])
            aabPayYtd = this.commonMethd.getEMP_YTD(this.commonData, mst82xRec[m82x.M82X_ALL_CODE_L], "A")
        }

        m8p5rec.setTaxPtdUnits(aabPayYtd.getTaxPtdUnits())
        m8p5rec.setTaxPtdAmtL(aabPayYtd.getTaxPtdAmtL())
        m8p5rec.setFisPtdUnits(aabPayYtd.getFisPtdUnits())
        m8p5rec.setFisPtdAmtL(aabPayYtd.getFisPtdAmtL())
        m8p5rec.setCalPtdUnits(aabPayYtd.getCalPtdUnits())
        m8p5rec.setCalPtdAmtL(aabPayYtd.getCalPtdAmtL())
        m8p5rec.setQtrPtdUnits(aabPayYtd.getQtrPtdUnits())
        m8p5rec.setQtrPtdAmtL(aabPayYtd.getQtyPtdAmtL())
        m8p5rec.setMthPtdUnits(aabPayYtd.getMthPtdUnits())
        m8p5rec.setMthPtdAmtL(aabPayYtd.getMthPtdAmtL())

        return m8p5rec
    }

}

public class AABPAYMSF8P6Rec {
    private String s8P6UUID
    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8P6Obj (AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8P6UUID = UUID.randomUUID()
        this.s8P6UUID = this.s8P6UUID.replaceAll("-","").substring(0, 32).toUpperCase()
        return this.commonData
    }

    public MSF8P6Rec populateMsf8p6 (ArrayList <String> mst82xRec, MSF8P6Rec msf8p6rec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8P6Rec m8p6rec = msf8p6rec
        this.commonMethd.initAABPAYCommonMethod(this.commonData)

        msf8p6rec.setPrimaryKey(new MSF8P6Key (this.s8P6UUID))
        msf8p6rec.setPayAdvEmpUuid(this.commonData.getPayAdvEmpUuid())
        msf8p6rec.setPayAdvDedUuid(this.commonData.getPayAdvDedUuid())
        msf8p6rec.setDednCode(mst82xRec[m82x.M82X_DED_CODE_N])
        msf8p6rec.setLastModDate(this.commonData.getLastModDate())
        msf8p6rec.setLastModTime(this.commonData.getLastModTime())
        msf8p6rec.setLastModUser(this.commonData.getLastModUser())

        AABPAY_YTD aabPayYtd = new AABPAY_YTD()
        aabPayYtd = this.commonMethd.getEMP_YTD(this.commonData, mst82xRec[m82x.M82X_DED_CODE_N], "D")
        msf8p6rec.setTaxPtdUnits(aabPayYtd.getTaxPtdUnits())
        msf8p6rec.setTaxPtdAmtL(aabPayYtd.getTaxPtdAmtL())
        msf8p6rec.setFisPtdUnits(aabPayYtd.getFisPtdUnits())
        msf8p6rec.setFisPtdAmtL(aabPayYtd.getFisPtdAmtL())
        msf8p6rec.setCalPtdUnits(aabPayYtd.getCalPtdUnits())
        msf8p6rec.setCalPtdAmtL(aabPayYtd.getCalPtdAmtL())
        msf8p6rec.setQtrPtdUnits(aabPayYtd.getQtrPtdUnits())
        msf8p6rec.setQtrPtdAmtL(aabPayYtd.getQtyPtdAmtL())
        msf8p6rec.setMthPtdUnits(aabPayYtd.getMthPtdUnits())
        msf8p6rec.setMthPtdAmtL(aabPayYtd.getMthPtdAmtL())

        return m8p6rec
    }

}


public class AABPAYMSF8P9Rec {

    private String s8P9UUID
    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8P9Obj(AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8P9UUID = UUID.randomUUID()
        this.s8P9UUID = this.s8P9UUID.replaceAll("-","").toUpperCase()
        return this.commonData
    }

    public MSF8P9Rec populateMsf8p9(ArrayList <String> mst82xRec, MSF8P9Rec msf8p9rec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8P9Rec m8p9rec = msf8p9rec
        this.commonMethd.initAABPAYCommonMethod(this.commonData)

        BigDecimal iTrnAmount = 0
        BigDecimal iTrnUnit = 0

        m8p9rec.setPrimaryKey(new MSF8P9Key(this.s8P9UUID))
        m8p9rec.setPayAdvEmpUuid(this.commonData.getPayAdvEmpUuid())
        m8p9rec.setPayAdvEanUuid(this.commonData.getPayAdvEanUuid())

        m8p9rec.setLastModDate(this.commonData.getLastModDate())
        m8p9rec.setLastModTime(this.commonData.getLastModTime())
        m8p9rec.setLastModUser(this.commonData.getLastModUser())

        // Based on MST82Z J Record
        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.J_RECORD)){
            this.commonMethd.info("MSF8P9 Uuid:" + this.s8P9UUID + " EarnCode:" + mst82xRec[m82x.M82X_EARN_CODE_J] + " TranDate:" + mst82xRec[m82x.M82X_TRN_DATE_J],getClass())

            m8p9rec.setEarnCode(mst82xRec[m82x.M82X_EARN_CODE_J])
            m8p9rec.setTranDate(mst82xRec[m82x.M82X_TRN_DATE_J])
            m8p9rec.setRateRefCode(mst82xRec[m82x.M82X_EARN_RATE_REF_J])
            m8p9rec.setHdRateRefCode(mst82xRec[m82x.M82X_HD_RATE_REF_CODE_J])

            m8p9rec.setTrnUnits(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_UNITS_J]))
            m8p9rec.setEquivUnits(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_EQUIV_UNITS_J]))
            m8p9rec.setAmount(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_AMT_J]))
            m8p9rec.setHdRateRefRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_HD_RATE_REF_RATE_J]))
            m8p9rec.setHourlyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_HRLY_RATE_J]))
            m8p9rec.setDailyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_DLY_RATE_J]))
            m8p9rec.setWeeklyRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_WKLY_RATE_J]))
            m8p9rec.setAnnualRate(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_ANN_RATE_J]))

            iTrnAmount = this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_AMT_J])
            iTrnUnit = this.commonMethd.strToBDec(mst82xRec[m82x.M82X_EARN_UNITS_J])
            this.commonMethd.info(iTrnAmount.toString(), getClass())
            this.commonMethd.info(iTrnUnit.toString(), getClass())
        }

        // Based on MST82Z L Record
        if (mst82xRec[m82x.M82X_RECORD_TYPE].equals(m82x.L_RECORD)){
            this.commonMethd.info("MSF8P9 Uuid:" + this.s8P9UUID + " EarnCode:" + mst82xRec[m82x.M82X_ALL_CODE_L] + " TranDate:" + mst82xRec[m82x.M82X_TRN_DATE_L],getClass())

            m8p9rec.setEarnCode(mst82xRec[m82x.M82X_ALL_CODE_L])
            m8p9rec.setTranDate(mst82xRec[m82x.M82X_TRN_DATE_L])
            m8p9rec.setRateRefCode(new String("      "))
            m8p9rec.setHdRateRefCode(mst82xRec[m82x.M82X_HD_RATE_REF_CODE_L])

            m8p9rec.setTrnUnits(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_ALL_UNITS_L]))
            m8p9rec.setEquivUnits(0)
            m8p9rec.setAmount(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_ALL_AMT_L]))
            m8p9rec.setHdRateRefRate(0)
            m8p9rec.setHourlyRate(0)
            m8p9rec.setDailyRate(0)
            m8p9rec.setWeeklyRate(0)
            m8p9rec.setAnnualRate(0)

            iTrnAmount = this.commonMethd.strToBDec(mst82xRec[m82x.M82X_ALL_AMT_L])
            iTrnUnit = this.commonMethd.strToBDec(mst82xRec[m82x.M82X_ALL_UNITS_L])
            this.commonMethd.info(iTrnAmount.toString(), getClass())
            this.commonMethd.info(iTrnUnit.toString(), getClass())
        }

        if (iTrnUnit == 0) {
            m8p9rec.setTrnRate(0)
        }else{
            m8p9rec.setTrnRate(iTrnAmount/iTrnUnit)
        }

        return m8p9rec
    }

}

public class AABPAYMSF8PARec {

    private String s8PAUUID

    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8PAObj(AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8PAUUID = UUID.randomUUID()
        this.s8PAUUID = this.s8PAUUID.replaceAll("-","").toUpperCase()
        return this.commonData
    }

    public MSF8PARec populateMsf8pA(ArrayList <String> mst82xRec, MSF8PARec msf8pArec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8PARec m8pArec = msf8pArec
        this.commonMethd.initAABPAYCommonMethod(this.commonData)
        this.commonMethd.info("MSF8PA Uuid:" + this.s8PAUUID + " DednCode:" + mst82xRec[m82x.M82X_DED_CODE_N],getClass())

        m8pArec.setPrimaryKey(new MSF8PAKey(this.s8PAUUID))
        m8pArec.setDednCode(mst82xRec[m82x.M82X_DED_CODE_N])
        m8pArec.setPayAdvEmpUuid(this.commonData.getPayAdvEmpUuid())
        m8pArec.setPayAdvDedUuid(this.commonData.getPayAdvDedUuid())
        m8pArec.setTranDate(this.commonData.getPeriodEndDate())
        m8pArec.setTrnUnits(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_DED_UNITS_N]))
        m8pArec.setAmount(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_DED_AMT_N]))
        m8pArec.setLastModDate(this.commonData.getLastModDate())
        m8pArec.setLastModTime(this.commonData.getLastModTime())
        m8pArec.setLastModUser(this.commonData.getLastModUser())

        return m8pArec
    }

}

public class AABPAYMSF8PBRec{
    private String s8PBUUID

    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();


    public AABPAYM8pCommonData initMSF8PBObj(AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8PBUUID = UUID.randomUUID()
        this.s8PBUUID = this.s8PBUUID.replaceAll("-","").toUpperCase()
        return this.commonData
    }

    public MSF8PBRec populateMsf8pB(ArrayList <String> mst82xRec, MSF8PBRec msf8pBrec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8PBRec m8pBrec = msf8pBrec
        this.commonMethd.initAABPAYCommonMethod(this.commonData)
        this.commonMethd.info("MSF8PB Uuid:" + this.s8PBUUID ,getClass())

        m8pBrec.setPrimaryKey(new MSF8PBKey(this.s8PBUUID))
        m8pBrec.setPayAdvEmpUuid(this.commonData.getPayAdvEmpUuid())
        m8pBrec.setBsbNumber(mst82xRec[m82x.M82X_BSB_NUMBER_F])
        m8pBrec.setBankAcctNo(mst82xRec[m82x.M82X_BANK_ACCT_NO_F])
        m8pBrec.setBsbDesc(mst82xRec[m82x.M82X_BSB_DESC_F])
        m8pBrec.setBankDesc(mst82xRec[m82x.M82X_BANK_DESC])
        m8pBrec.setAmount(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_NETT_AMT_F]))
        List <String> empBankDetails = new ArrayList<String>()
        empBankDetails.add(mst82xRec[m82x.M82X_BSB_NUMBER_F])
        empBankDetails.add(mst82xRec[m82x.M82X_BANK_ACCT_NO_F])
        MSF822Rec msf822Rec = this.commonMethd.getEmpBankDetails(this.commonData, empBankDetails)
        m8pBrec.setBankAcctName(msf822Rec.getBankAcctName())
        m8pBrec.setBankAcctTy(msf822Rec.getBankAcctTy())
        m8pBrec.setBankingType(msf822Rec.getBankingType())

        m8pBrec.setLastModDate(this.commonData.getLastModDate())
        m8pBrec.setLastModTime(this.commonData.getLastModTime())
        m8pBrec.setLastModUser(this.commonData.getLastModUser())

        return m8pBrec
    }
}

public class AABPAYMSF8PCRec {

    private String s8PCUUID

    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8PCObj(AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8PCUUID = UUID.randomUUID()
        this.s8PCUUID = this.s8PCUUID.replaceAll("-","").toUpperCase()
        return this.commonData
    }

    public MSF8PCRec populateMsf8pC(ArrayList <String> mst82xRec, MSF8PCRec msf8pCrec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8PCRec m8pCrec = msf8pCrec
        this.commonMethd.initAABPAYCommonMethod(this.commonData)
        this.commonMethd.info("MSF8PC Uuid:" + this.s8PCUUID,getClass())

        m8pCrec.setPrimaryKey(new MSF8PCKey(this.s8PCUUID))
        m8pCrec.setPayAdvEmpUuid(this.commonData.getPayAdvEmpUuid())
        m8pCrec.setPslMessage(mst82xRec[m82x.M82X_MSG_B])
        m8pCrec.setMessageType("EM")
        m8pCrec.setLastModDate(this.commonData.getLastModDate())
        m8pCrec.setLastModTime(this.commonData.getLastModTime())
        m8pCrec.setLastModUser(this.commonData.getLastModUser())

        return m8pCrec
    }

    public MSF8PCRec populateMsf8pC(MSF839Rec msf839rec){
        MSF8PCRec m8pCrec = new MSF8PCRec()
        this.commonMethd.initAABPAYCommonMethod(this.commonData)
        this.commonMethd.info("MSF8PC Uuid:" + this.s8PCUUID,getClass())

        m8pCrec.setPrimaryKey(new MSF8PCKey(this.s8PCUUID))
        m8pCrec.setPayAdvEmpUuid(this.commonData.getPayAdvEmpUuid())
        m8pCrec.setPslMessage(msf839rec.getPayslipMess_1() + " " + msf839rec.getPayslipMess_2())
        m8pCrec.setMessageType(msf839rec.getPrimaryKey().getMessageType())
        m8pCrec.setLastModDate(this.commonData.getLastModDate())
        m8pCrec.setLastModTime(this.commonData.getLastModTime())
        m8pCrec.setLastModUser(this.commonData.getLastModUser())

        return m8pCrec
    }
}

public class AABPAYMSF8PDRec {

    private String s8PDUUID

    private AABPAYM8pCommonData commonData = new AABPAYM8pCommonData();
    private AABPAYCommonMethod commonMethd = new AABPAYCommonMethod();

    public AABPAYM8pCommonData initMSF8PDObj(AABPAYM8pCommonData inpCommonData){
        this.commonData = inpCommonData
        this.s8PDUUID = UUID.randomUUID()
        this.s8PDUUID = this.s8PDUUID.replaceAll("-","").toUpperCase()
        return this.commonData
    }

    public MSF8PDRec populateMsf8pD(ArrayList <String> mst82xRec, MSF8PDRec msf8pDrec){
        AABPAYMst82xRec m82x = new AABPAYMst82xRec();
        MSF8PDRec m8pDrec = msf8pDrec
        this.commonMethd.initAABPAYCommonMethod(this.commonData)
        this.commonMethd.info("MSF8PD Uuid:" + this.s8PDUUID,getClass())

        m8pDrec.setPrimaryKey(new MSF8PDKey(this.s8PDUUID))
        m8pDrec.setPayAdvEmpUuid(this.commonData.getPayAdvEmpUuid())
        m8pDrec.setLeaveType(mst82xRec[m82x.M82X_LEAVE_TYPE_D])
        m8pDrec.setLeaveDesc(mst82xRec[m82x.M82X_LEAVE_DESC_D])
        m8pDrec.setUnitType(mst82xRec[m82x.M82X_LEAVE_UNIT_TYPE_D].trim())
        m8pDrec.setLeaveBalance(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_LEAVE_BALANCE_D]))
        m8pDrec.setLveEntitled(this.commonMethd.strToBDec(mst82xRec[m82x.M82X_LEAVE_ENT_BAL_D]))
        m8pDrec.setLastModDate(this.commonData.getLastModDate())
        m8pDrec.setLastModTime(this.commonData.getLastModTime())
        m8pDrec.setLastModUser(this.commonData.getLastModUser())

        MSF880Rec msf880Rec = this.commonMethd.getMSF880Rec(this.commonData,m8pDrec.getLeaveType())
        m8pDrec.setLveAccrued(msf880Rec.getLveAccrued())
        
        if (!msf880Rec.getAccrualCode().isEmpty()){
            MSF801_R_801Rec msf801RRec = this.commonMethd.getMSF801_R(this.commonData, m8pDrec.getLeaveType()+msf880Rec.getAccrualCode())
            m8pDrec.setAccOrEnt(msf801RRec.getAccOrEntR())
        }

        return m8pDrec
    }

}


public class AABPAYMsf8pSeriesRec {
    private MSF8P1Rec msf8p1Rec
    private ArrayList<MSF8P2Rec> listMsf8p2Rec
    private ArrayList<MSF8P3Rec> listMsf8p3Rec
    private ArrayList<MSF8P4Rec> listMsf8p4Rec
    private ArrayList<MSF8P5Rec> listMsf8p5Rec
    private ArrayList<MSF8P6Rec> listMsf8p6Rec
    private ArrayList<MSF8P7Rec> listMsf8p7Rec
    private ArrayList<MSF8P8Rec> listMsf8p8Rec
    private ArrayList<MSF8P9Rec> listMsf8p9Rec
    private ArrayList<MSF8PARec> listMsf8pARec
    private ArrayList<MSF8PBRec> listMsf8pBRec
    private ArrayList<MSF8PCRec> listMsf8pCRec
    private ArrayList<MSF8PDRec> listMsf8pDRec
    private ArrayList<MSF8PERec> listMsf8pERec

    public void initMsf8PSeriesRec(){
        this.msf8p1Rec = new MSF8P1Rec()
        this.listMsf8p2Rec = new ArrayList<MSF8P2Rec>()
        this.listMsf8p3Rec = new ArrayList<MSF8P3Rec>()
        initEmpRecord()

    }

    public void initEmpRecord(){
        this.listMsf8p4Rec = new ArrayList<MSF8P4Rec>()
        this.listMsf8p5Rec = new ArrayList<MSF8P5Rec>()
        this.listMsf8p6Rec = new ArrayList<MSF8P6Rec>()
        this.listMsf8p7Rec = new ArrayList<MSF8P7Rec>()
        this.listMsf8p8Rec = new ArrayList<MSF8P8Rec>()
        this.listMsf8p9Rec = new ArrayList<MSF8P9Rec>()
        this.listMsf8pARec = new ArrayList<MSF8PARec>()
        this.listMsf8pBRec = new ArrayList<MSF8PBRec>()
        this.listMsf8pCRec = new ArrayList<MSF8PCRec>()
        this.listMsf8pDRec = new ArrayList<MSF8PDRec>()
        this.listMsf8pERec = new ArrayList<MSF8PERec>()
    }

    public MSF8P1Rec getMsf8p1Rec(){
        return this.msf8p1Rec
    }

    public MSF8P4Rec getMsf8p4Rec(Integer iIndex){
        return this.listMsf8p4Rec.get(iIndex)
    }

    public ArrayList <MSF8P2Rec> getListMsf8p2Rec(){
        return this.listMsf8p2Rec
    }

    public ArrayList <MSF8P3Rec> getListMsf8p3Rec(){
        return this.listMsf8p3Rec
    }

    public ArrayList <MSF8P4Rec> getListMsf8p4Rec(){
        return this.listMsf8p4Rec
    }

    public ArrayList <MSF8P5Rec> getListMsf8p5Rec(){
        return this.listMsf8p5Rec
    }

    public ArrayList <MSF8P6Rec> getListMsf8p6Rec(){
        return this.listMsf8p6Rec
    }

    public ArrayList <MSF8P7Rec> getListMsf8p7Rec(){
        return this.listMsf8p7Rec
    }

    public ArrayList <MSF8P8Rec> getListMsf8p8Rec(){
        return this.listMsf8p8Rec
    }

    public ArrayList <MSF8P9Rec> getListMsf8p9Rec(){
        return this.listMsf8p9Rec
    }

    public ArrayList <MSF8PARec> getListMsf8pARec(){
        return this.listMsf8pARec
    }

    public ArrayList <MSF8PBRec> getListMsf8pBRec(){
        return this.listMsf8pBRec
    }

    public ArrayList <MSF8PCRec> getListMsf8pCRec(){
        return this.listMsf8pCRec
    }

    public ArrayList <MSF8PDRec> getListMsf8pDRec(){
        return this.listMsf8pDRec
    }

    public ArrayList <MSF8PERec> getListMsf8pERec(){
        return this.listMsf8pERec
    }

    public Integer getCurrIndexMsf8p4Rec(){
        if (this.listMsf8p4Rec.isEmpty()){
            return 0
        }else{
            return (this.listMsf8p4Rec.size() -1)}
    }

    public void setMsf8p1Rec(MSF8P1Rec rMsf8p1Rec){
        this.msf8p1Rec = rMsf8p1Rec
    }

    public void setListMsf8p2Rec (ArrayList <MSF8P2Rec> lMsf8p2Rec){
        this.listMsf8p2Rec = lMsf8p2Rec
    }

    public void setListMsf8p3Rec (ArrayList <MSF8P3Rec> lMsf8p3Rec){
        this.listMsf8p3Rec = lMsf8p3Rec
    }

    public void setListMsf8p4Rec (ArrayList <MSF8P4Rec> lMsf8p4Rec){
        this.listMsf8p4Rec = lMsf8p4Rec
    }

    public void setListMsf8p5Rec (ArrayList <MSF8P5Rec> lMsf8p5Rec){
        this.listMsf8p5Rec = lMsf8p5Rec
    }

    public void setListMsf8p6Rec (ArrayList <MSF8P6Rec> lMsf8p6Rec){
        this.listMsf8p6Rec = lMsf8p6Rec
    }

    public void setListMsf8p7Rec (ArrayList <MSF8P7Rec> lMsf8p7Rec){
        this.listMsf8p7Rec = lMsf8p7Rec
    }

    public void setListMsf8p8Rec (ArrayList <MSF8P8Rec> lMsf8p8Rec){
        this.listMsf8p8Rec = lMsf8p8Rec
    }

    public void setListMsf8p9Rec (ArrayList <MSF8P9Rec> lMsf8p9Rec){
        this.listMsf8p9Rec = lMsf8p9Rec
    }

    public void setListMsf8pARec (ArrayList <MSF8PARec> lMsf8pARec){
        this.listMsf8pARec = lMsf8pARec
    }

    public void setListMsf8pBRec (ArrayList <MSF8PBRec> lMsf8pBRec){
        this.listMsf8pBRec = lMsf8pBRec
    }

    public void setListMsf8pCRec (ArrayList <MSF8PCRec> lMsf8pCRec){
        this.listMsf8pCRec = lMsf8pCRec
    }

    public void setListMsf8pDRec (ArrayList <MSF8PDRec> lMsf8pDRec){
        this.listMsf8pDRec = lMsf8pDRec
    }

    public void setListMsf8pERec (ArrayList <MSF8PERec> lMsf8pERec){
        this.listMsf8pERec = lMsf8pERec
    }


    public void addMsf8p2Rec (MSF8P2Rec msf8p2){
        this.listMsf8p2Rec.add(msf8p2)
    }

    public void addMsf8p3Rec (MSF8P3Rec msf8p3){
        this.listMsf8p3Rec.add(msf8p3)
    }

    public void addMsf8p4Rec (MSF8P4Rec msf8p4){
        this.listMsf8p4Rec.add(msf8p4)
    }

    public void addMsf8p5Rec (MSF8P5Rec msf8p5){
        this.listMsf8p5Rec.add(msf8p5)
    }

    public void addMsf8p6Rec (MSF8P6Rec msf8p6){
        this.listMsf8p6Rec.add(msf8p6)
    }

    public void addMsf8p7Rec (MSF8P7Rec msf8p7){
        this.listMsf8p7Rec.add(msf8p7)
    }

    public void addMsf8p8Rec (MSF8P8Rec msf8p8){
        this.listMsf8p8Rec.add(msf8p8)
    }

    public void addMsf8p9Rec (MSF8P9Rec msf8p9){
        this.listMsf8p9Rec.add(msf8p9)
    }

    public void addMsf8pARec (MSF8PARec msf8pA){
        this.listMsf8pARec.add(msf8pA)
    }

    public void addMsf8pBRec (MSF8PBRec msf8pB){
        this.listMsf8pBRec.add(msf8pB)
    }

    public void addMsf8pCRec (MSF8PCRec msf8pC){
        this.listMsf8pCRec.add(msf8pC)
    }

    public void addMsf8pDRec (MSF8PDRec msf8pD){
        this.listMsf8pDRec.add(msf8pD)
    }

    public void addMsf8pERec (MSF8PERec msf8pE){
        this.listMsf8pERec.add(msf8pE)
    }

    public void edtListMsf8p4Rec (Integer iIndex, MSF8P4Rec msf8p4){
        this.listMsf8p4Rec.set(iIndex, msf8p4)
    }
}

public class AABPAYMst82xRec {
    public static final String A_RECORD = "A"
    public static final String B_RECORD = "B"
    public static final String C_RECORD = "C"
    public static final String D_RECORD = "D"
    public static final String E_RECORD = "E"
    public static final String F_RECORD = "F"
    public static final String G_RECORD = "G"
    public static final String H_RECORD = "H"
    public static final String I_RECORD = "I"
    public static final String J_RECORD = "J"
    public static final String K_RECORD = "K"
    public static final String L_RECORD = "L"
    public static final String M_RECORD = "M"
    public static final String N_RECORD = "N"

    // MSF82X indexes

    public static final Integer M82X_RUN_NO = 0
    public static final Integer M82X_PAY_LOCATION = 1
    public static final Integer M82X_PHYSICAL_LOC = 2
    public static final Integer M82X_WORK_LOCATION = 3
    public static final Integer M82X_PAYSLIP_TYPE = 4
    public static final Integer M82X_SURNAME = 5
    public static final Integer M82X_FIRST_NAME = 6
    public static final Integer M82X_SECOND_INITIAL = 7
    public static final Integer M82X_EMPLOYEE_ID = 8
    public static final Integer M82X_RECORD_TYPE = 9

    //A record
    public static final Integer M82X_PAYSLIP_DESC_A    = 10
    public static final Integer M82X_PRV_PRD_NO_PG_A   = 11
    public static final Integer M82X_PRV_STR_DT_PG_A   = 12
    public static final Integer M82X_PRV_END_DT_PG_A   = 13
    public static final Integer M82X_SURNAME_A         = 14
    public static final Integer M82X_FIRST_NAME_A      = 15
    public static final Integer M82X_SECOND_NAME_A     = 16
    public static final Integer M82X_EMPLOYEE_ID_A     = 17
    public static final Integer M82X_AUTO_PAID_IND_A   = 18
    public static final Integer M82X_POST_IT_IND_A     = 19
    public static final Integer M82X_ADDRESS_1_A       = 20
    public static final Integer M82X_ADDRESS_2_A       = 21
    public static final Integer M82X_ADDRESS_3_A       = 22
    public static final Integer M82X_POST_STATE_A      = 23
    public static final Integer M82X_POST_CODE_A       = 24
    public static final Integer M82X_PRC_1_A           = 25
    public static final Integer M82X_PRC_DESC_1_A      = 26
    public static final Integer M82X_PRC_2_A           = 27
    public static final Integer M82X_PRC_DESC_2_A      = 28
    public static final Integer M82X_PRC_3_A           = 29
    public static final Integer M82X_PRC_DESC_3_A      = 30
    public static final Integer M82X_PRC_4_A           = 31
    public static final Integer M82X_PRC_DESC_4_A      = 32
    public static final Integer M82X_PRC_5_A           = 33
    public static final Integer M82X_PRC_DESC_5_A      = 34
    public static final Integer M82X_PRC_6_A           = 35
    public static final Integer M82X_PRC_DESC_6_A      = 36
    public static final Integer M82X_PRC_7_A           = 37
    public static final Integer M82X_PRC_DESC_7_A      = 38
    public static final Integer M82X_PRC_8_A           = 39
    public static final Integer M82X_PRC_DESC_8_A      = 40
    public static final Integer M82X_PRC_9_A           = 41
    public static final Integer M82X_PRC_DESC_9_A      = 42
    public static final Integer M82X_PRC_10_A          = 43
    public static final Integer M82X_PRC_DESC_10_A     = 44
    public static final Integer M82X_PAY_LOC_DESC_A    = 45
    public static final Integer M82X_WORK_GROUP_A      = 46
    public static final Integer M82X_WORK_GROUP_DESC_A = 47
    public static final Integer M82X_POSITION_ID_A     = 48
    public static final Integer M82X_POSITION_TITLE_A  = 49
    public static final Integer M82X_PHYS_LOCN_DESC_A  = 50
    public static final Integer M82X_RATE_REF_A        = 51
    public static final Integer M82X_RATE_REF_DESC_A   = 52
    public static final Integer M82X_HRLY_RATE_A       = 53
    public static final Integer M82X_DAILY_RATE_A      = 54
    public static final Integer M82X_WEEKLY_RATE_A     = 55
    public static final Integer M82X_PRD_RATE_A        = 56
    public static final Integer M82X_ANN_RATE_A        = 57
    public static final Integer M82X_DEP_RBTE_A        = 58
    public static final Integer M82X_PAY_GROUP_A       = 59
    public static final Integer M82X_PAY_FREQ_A        = 60
    public static final Integer M82X_TAX_SCALE_A       = 61
    public static final Integer M82X_HIRE_DATE         = 62
    public static final Integer M82X_EMPLOYEE_CLASS_A  = 63

    //B record
    public static final Integer M82X_MSG_B             = 10

    //C record
    public static final Integer M82X_TRAN_DATE_C = 10
    public static final Integer M82X_DAY_INDICATOR_C = 11
    public static final Integer M82X_SHIFT_TYPE_C = 12
    public static final Integer M82X_NORMAL_UNITS_C = 13
    public static final Integer M82X_OT_UNITS_C = 14
    public static final Integer M82X_OT_RATE_C = 15
    public static final Integer M82X_HIGH_GR_IND_C = 16
    public static final Integer M82X_ABSENT_UNITS_C = 17
    public static final Integer M82X_ABSENT_RSN_C = 18
    public static final Integer M82X_ABSENT_TYPE_C = 19
    public static final Integer M82X_AMOUNT_C = 20
    public static final Integer M82X_TRAN_CODE_C = 21
    public static final Integer M82X_TRAN_DESC_C = 22
    public static final Integer M82X_TRAN_SH_DESC_C = 23
    public static final Integer M82X_PAYSLIP_BOX_IND_C = 24
    public static final Integer M82X_HD_RATE_REF_C = 25
    public static final Integer M82X_RDO_IND_C = 26
    public static final Integer M82X_SHIFT_CLASS_C = 27

    //D record
    public static final Integer M82X_LEAVE_TYPE_D = 10;
    public static final Integer M82X_LEAVE_DESC_D = 11;
    public static final Integer M82X_LEAVE_UNIT_TYPE_D = 12;
    public static final Integer M82X_LEAVE_BALANCE_D = 13;
    public static final Integer M82X_LEAVE_ACC_BAL_D = 14;
    public static final Integer M82X_LEAVE_ENT_BAL_D = 15;
    public static final Integer M82X_LEAVE_ACC_IND_D = 16;

    //E record
    public static final Integer M82X_EARN_TYPE_E = 10;
    public static final Integer M82X_EARN_CLASS_E = 11;
    public static final Integer M82X_PAYSLIP_BOX_E = 12;
    public static final Integer M82X_MISC_REP_FLDS_01_E = 13;
    public static final Integer M82X_MISC_REP_FLDS_02_E = 14;
    public static final Integer M82X_MISC_REP_FLDS_03_E = 15;
    public static final Integer M82X_MISC_REP_FLDS_04_E = 16;
    public static final Integer M82X_MISC_REP_FLDS_05_E = 17;
    public static final Integer M82X_LEAVE_DESC_E = 18;
    public static final Integer M82X_LEAVE_SH_DESC_E = 19;
    public static final Integer M82X_LEAVE_TAKEN_E = 20;

    //F record
    public static final Integer M82X_BSB_NUMBER_F = 10;
    public static final Integer M82X_BSB_DESC_F = 11;
    public static final Integer M82X_BANK_ACCT_NO_F = 12;
    public static final Integer M82X_NETT_AMT_F = 13;
    public static final Integer M82X_PAY_METHOD_F = 14;
    public static final Integer M82X_BANK_NO_F = 15;
    public static final Integer M82X_BANK_TYPE = 16;
    public static final Integer M82X_BANK_DESC = 17;

    //G record
    public static final Integer M82X_SUPER_TYPE_G =10;
    public static final Integer M82X_SUPER_DESC_G = 11;
    public static final Integer M82X_SUPER_SH_DESC_G = 12;
    public static final Integer M82X_CUR_VALUE_G = 13;
    public static final Integer M82X_YTD_VALUE_G = 14;

    //H record
    public static final Integer M82X_CUR_GROSS_VALUE_H = 10;
    public static final Integer M82X_CUR_TAX_VALUE_H = 11;
    public static final Integer M82X_CUR_DED_VALUE_H = 12;
    public static final Integer M82X_CUR_NETT_VALUE_H = 13;
    public static final Integer M82X_CUR_UNTX_AMT_H = 14;
    public static final Integer M82X_CUR_PRETAX_DED_H = 15;
    public static final Integer M82X_CUR_PAY_DESC_H = 16;
    public static final Integer M82X_CUR_COMP_HRLY_RATE = 17;
    public static final Integer M82X_CUR_COMP_DAILY_RATE = 18;
    public static final Integer M82X_CUR_COMP_WEEKLY_RATE = 19;
    public static final Integer M82X_CUR_COMP_PRD_RATE = 20;
    public static final Integer M82X_CUR_COMP_ANN_RATE = 21;
    public static final Integer M82X_PAY_DATE_H = 22;
    public static final Integer M82X_EMPLOYER_NAME_H = 23;
    public static final Integer M82X_EMPLOYER_ABN_H = 24;

    //I record
    public static final Integer M82X_FIS_YTD_GROSS_I = 10;
    public static final Integer M82X_TAX_YTD_GROSS_I = 11;
    public static final Integer M82X_FIS_YTD_EMP_TAX_I = 12;
    public static final Integer M82X_TAX_YTD_EMP_TAX_I = 13;
    public static final Integer M82X_FIS_YTD_DED_I = 14;
    public static final Integer M82X_TAX_YTD_DED_I = 15;
    public static final Integer M82X_FIS_YTD_NET_I = 16;
    public static final Integer M82X_TAX_YTD_NET_I = 17
    public static final Integer M82X_YTD_UNTX_EARN_I = 18;
    public static final Integer M82X_YTD_PRETX_DED_I = 19;

    //J record
    public static final Integer M82X_EARN_CODE_J = 10;
    public static final Integer M82X_EARN_DESC_J = 11;
    public static final Integer M82X_EARN_SH_DESC_J = 12;
    public static final Integer M82X_EARN_RATE_REF_J = 13;
    public static final Integer M82X_EARN_UNITS_J = 14;
    public static final Integer M82X_EARN_EQUIV_UNITS_J = 15;
    public static final Integer M82X_EARN_AMT_J = 16;
    public static final Integer M82X_EARN_HRLY_RATE_J = 17;
    public static final Integer M82X_EARN_WKLY_RATE_J = 18;
    public static final Integer M82X_EARN_DLY_RATE_J = 19;
    public static final Integer M82X_EARN_ANN_RATE_J = 20;
    public static final Integer M82X_PAYSLIP_BOX_IND_J = 21;
    public static final Integer M82X_YTD_EARN_AMT_J = 22;
    public static final Integer M82X_HD_RATE_REF_CODE_J = 23;
    public static final Integer M82X_HD_RATE_REF_RATE_J = 24;
    public static final Integer M82X_PAY_METHOD_J = 25;
    public static final Integer M82X_TRN_DATE_J = 26;
    public static final Integer M82X_EARN_CLASS_J = 27;

    //K record
    public static final Integer M82X_TOTAL_EARN_K = 10;

    //L record
    public static final Integer M82X_ALL_CODE_L = 10;
    public static final Integer M82X_ALL_DESC_L = 11;
    public static final Integer M82X_ALL_SHORT_DESC_L = 12;
    public static final Integer M82X_ALL_UNITS_L = 13;
    public static final Integer M82X_ALL_RATE_L = 14;
    public static final Integer M82X_ALL_AMT_L = 15;
    public static final Integer M82X_PAYSLIP_BOX_IND_L = 16;
    public static final Integer M82X_YTD_ALL_AMT_L = 17;
    public static final Integer M82X_HD_RATE_REF_CODE_L = 18;
    public static final Integer M82X_HD_RATE_REF_RATE_L = 19;
    public static final Integer M82X_PAY_METHOD_L = 20;
    public static final Integer M82X_TRN_DATE_L = 21;

    //M record
    public static final Integer M82X_TOTAL_ALL_M = 10;

    //N record
    public static final Integer M82X_DED_CODE_N = 10;
    public static final Integer M82X_DED_LONG_DESC_N = 11;
    public static final Integer M82X_DED_DESC_N = 12;
    public static final Integer M82X_DED_AMT_N = 13;
    public static final Integer M82X_YTD_VALUE_N = 14;
    public static final Integer M82X_PRE_TAX_IND_N = 15;
    public static final Integer M82X_MISC_RPT_FLD_01_N = 16;
    public static final Integer M82X_MISC_RPT_FLD_02_N = 17;
    public static final Integer M82X_MISC_RPT_FLD_03_N = 18;
    public static final Integer M82X_MISC_RPT_FLD_04_N = 19;
    public static final Integer M82X_MISC_RPT_FLD_05_N = 20;
    public static final Integer M82X_DED_TYPE_N = 21;
    public static final Integer M82X_DED_METHOD_N = 22;
    public static final Integer M82X_DED_UNITS_N = 23;
    public static final Integer M82X_PAYSLIP_BOX_N = 24;

    //P record
    public static final Integer M82X_TOTAL_DED_P = 10;

    //Q record
    public static final Integer M82X_TAX_SCALE_Q = 10;
    public static final Integer M82X_TAX_FILE_NO_Q = 11;
    public static final Integer M82X_ID_NUMBER_Q = 12;
    public static final Integer M82X_PASSPORT_NO_Q = 13;
    public static final Integer M82X_COMPANY_CC_NO_Q = 14;
    public static final Integer M82X_NATURE_OF_PERSON_Q = 15;
    public static final Integer M82X_TAX_OFFICE_Q = 16;
    public static final Integer M82X_UIF_STATUS_Q = 17;
    public static final Integer M82X_UIF_REASON_Q = 18;
    public static final Integer M82X_DIR_NO_Q = 19;
    public static final Integer M82X_DIR_AMOUNT_Q = 20;
    public static final Integer M82X_DIR_PERCENT_Q = 21;
    public static final Integer M82X_PAYE_ONLY_Q = 22;
    public static final Integer M82X_FIXED_RATE_Q = 23;
    public static final Integer M82X_VOL_DED_Q = 24;
    public static final Integer M82X_COIDA_ASS_CAT_Q = 25;
    public static final Integer M82X_DIRECTOR_IND_Q = 26;
    public static final Integer M82X_IT3_REASON_CODE_Q = 27;
    public static final Integer M82X_TAX_CERT_ISS_Q = 28;
}

public class AABPAYM8pCommonData {
    String lastModDate
    String lastModTime
    String lastModUser
    String payAdvHdrUuid
    String payAdvEmpUuid
    String payAdvEanUuid
    String payAdvDedUuid
    String payGroup
    String empId
    String periodEndDate
    String periodStartDate
    String consYTDTots
    String payRunType
    Binding sessionBinding

}

public class AABPAYCommonMethod {
    private EDOIWrapper edoi;
    private Restart restart;
    private CommAreaScriptWrapper commarea;
    private Integer maxRecordRead

    public BigDecimal strToBDec (String inpStringOrig){
        info("strToBDec:" + inpStringOrig ,getClass())
        String inpString = inpStringOrig.trim()
        if (inpString != null){
            try{
                if (inpString.substring(0,1) == "+"){
                    return new BigDecimal(inpString.substring(1))
                }else{
                    return new BigDecimal(inpString)
                }

            }catch(Exception e){
                info ("Invalid Numeric :" + inpString + " Defaulted to 0",getClass())
                return 0
            }
        }else {
            return 0
        }

    }

    public Integer getMaxRecordRead(){
        return this.maxRecordRead
    }
    
    public void initAABPAYCommonMethod (AABPAYM8pCommonData commonData){
        info("initAABPAYCommonMethod",getClass())
        this.edoi = commonData.getSessionBinding().getVariable("edoi");
        this.commarea = commonData.getSessionBinding().getVariable("commarea");
        this.restart = commonData.getSessionBinding().getVariable("restart");
        this.maxRecordRead = 1000
    }
    public void info(String value, Class clazz){
        def logObject = LoggerFactory.getLogger(clazz);
        logObject.info("------------- " + value)
    }

    public MSF000_DC0004Rec getMSF000_DC0004 (AABPAYM8pCommonData commonData){
        info("getMSF000_DC0004",getClass())

        MSF000_DC0004Rec msf000_dc0004 = new MSF000_DC0004Rec()
        Constraint c1 = MSF000_DC0004Key.dstrctCode.equalTo(this.commarea.District)
        def query = new QueryImpl(MSF000_DC0004Rec.class).and(c1);
        try{
            msf000_dc0004 = this.edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf000_dc0004 == null){
            info("Unexpected value msf000_dc0004=null",getClass())
            msf000_dc0004 = new MSF000_DC0004Rec()
        }
        return msf000_dc0004
    }

    public MSF010Rec getMSF010Rec (String sTableType, String sTableCode){
        info("getMSF010Rec",getClass())

        MSF010Rec msf010Rec = new MSF010Rec()

        if (sTableType.trim() == "" || sTableCode.trim() == ""){
            info("Input not complete for getMSF010Rec - TableType:"+sTableType+" TableCode:"+sTableCode,getClass())
            return msf010Rec
        }
        Constraint c1 = MSF010Key.tableType.equalTo(sTableType)
        Constraint c2 = MSF010Key.tableCode.equalTo(sTableCode)

        def query = new QueryImpl(MSF010Rec.class).and(c1).and(c2);

        try{
            msf010Rec = this.edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf010Rec == null){
            info("Unexpected value msf010Rec=null",getClass())
            msf010Rec = new MSF010Rec()
        }

        return msf010Rec
    }
    public MSF200Rec getMSF200Rec (AABPAYM8pCommonData commonData,String sSupplierNo){
        info("getMSF200Rec",getClass())

        MSF200Rec msf200Rec = new MSF200Rec()

        if (sSupplierNo.trim() == ""){
            info("Input not complete for getMSF200Rec - SupplierNo:"+sSupplierNo,getClass())
            return msf200Rec
        }

        Constraint c1 = MSF200Key.supplierNo.equalTo(sSupplierNo)
        def query = new QueryImpl (MSF200Rec.class).and(c1)

        try{
            msf200Rec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf200Rec == null){
            info("Unexpected value msf200Rec=null",getClass())
            msf200Rec = new MSF200Rec()
        }

        return msf200Rec
    }

    public MSF723Rec getMSF723Rec (AABPAYM8pCommonData commonData, String sWorkGroup){
        info("getMSF723Rec",getClass())

        MSF723Rec msf723Rec = new MSF723Rec()

        if (sWorkGroup.trim() == ""){
            info("Input not complete for getMSF723Rec - WorkGroup:"+sWorkGroup,getClass())
            return msf723Rec
        }

        String sRecType = "W"
        Constraint c1 = MSF723Key.workGroup.equalTo(sWorkGroup)
        Constraint c2 = MSF723Key.rec_723Type.equalTo(sRecType)
        Constraint c3 = MSF723Key.employeeId.equalTo(commonData.getEmpId())
        def query = new QueryImpl (MSF723Rec.class).and(c1).and(c2).and(c3)

        try{
            msf723Rec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf723Rec == null){
            info("Unexpected value msf723Rec=null",getClass())
            msf723Rec = new MSF723Rec()
        }

        return msf723Rec
    }

    public MSF760Rec getMSF760Rec (AABPAYM8pCommonData commonData){
        info("getMSF760Rec",getClass())

        MSF760Rec msf760Rec = new MSF760Rec()
        Constraint c1 = MSF760Key.employeeId.equalTo(commonData.getEmpId())

        def query = new QueryImpl (MSF760Rec.class).and(c1)

        try{
            msf760Rec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf760Rec == null){
            info("Unexpected value msf760Rec=null",getClass())
            msf760Rec = new MSF760Rec()
        }

        return msf760Rec
    }

    public MSF820Rec getMSF820Rec (AABPAYM8pCommonData commonData){
        info("getMSF820Rec",getClass())

        MSF820Rec msf820Rec = new MSF820Rec()
        Constraint c1 = MSF820Key.employeeId.equalTo(commonData.getEmpId())

        def query = new QueryImpl (MSF820Rec.class).and(c1)

        try{
            msf820Rec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf820Rec == null){
            info("Unexpected value msf820Rec=null",getClass())
            msf820Rec = new MSF820Rec()
        }

        return msf820Rec
    }

    public MSF822Rec getEmpBankDetails (AABPAYM8pCommonData commonData, List <String> aEmpBankDetails){
        info("getEmpBankDetails",getClass())

        MSF822Rec msf822Rec = new MSF822Rec()

        if (aEmpBankDetails.size()<1){
            info("Input not complete for getEmpBankDetails - branchCode and bankAcctNo",getClass())
            return msf822Rec
        }

        String conYTDTots
        String YES_STRING = "Y"
        String A_STRING = "A"
        String B_STRING = "B"

        if (commonData.getConsYTDTots().equals(YES_STRING)){
            conYTDTots = "   "
        }else{
            conYTDTots = commonData.getPayGroup()
        }

        Constraint c1 = MSF822Key.employeeId.equalTo(commonData.getEmpId())
        Constraint c2 = MSF822Key.consPayGrp.equalTo(conYTDTots)
        Constraint c3 = MSF822Rec.bankingType.equalTo(A_STRING)
        Constraint c4 = MSF822Rec.bankingType.equalTo(B_STRING)
        Constraint c5 = MSF822Rec.branchCode.equalTo(aEmpBankDetails[0])
        Constraint c6 = MSF822Rec.bankAcctNo.equalTo(aEmpBankDetails[1])
        def query = new QueryImpl (MSF822Rec.class).and(c1).and(c2).and(c5).and(c6).and(c3.or(c4))

        try{
            msf822Rec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf822Rec == null){
            info("Unexpected value msf820Rec=null",getClass())
            msf822Rec = new MSF822Rec()
        }

        return msf822Rec

    }

    public MSF810Rec getMSF810Rec (AABPAYM8pCommonData commonData){
        info("getMSF810Rec",getClass())

        MSF810Rec msf810Rec = new MSF810Rec()
        Constraint c1 = MSF810Key.employeeId.equalTo(commonData.getEmpId())
        def query = new QueryImpl (MSF810Rec.class).and(c1)
        msf810Rec = edoi.firstRow(query)

        try{
            msf810Rec = edoi.firstRow(query)
        }catch(Exception e){
            info(e.getMessage(),getClass())
        }

        if (msf810Rec == null){
            info("Unexpected value msf810Rec=null",getClass())
            msf810Rec = new MSF810Rec()
        }

        return msf810Rec
    }
    public MSF801_C0_801Rec getMSF801_C0 (String sAwardCode){
        info("getMSF801_C0",getClass())

        MSF801_C0_801Rec msf801C0Rec = new MSF801_C0_801Rec()
        Constraint c1 = MSF801_C0_801Key.cntlRecType.equalTo("C0")
        Constraint c2 = MSF801_C0_801Key.cntlKeyRest.equalTo(sAwardCode)

        def query = new QueryImpl (MSF801_C0_801Rec.class).and(c1).and(c2)

        try{
            msf801C0Rec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf801C0Rec == null){
            info("Unexpected value msf801C0Rec=null",getClass())
            msf801C0Rec = new MSF801_C0_801Rec()
        }

        return msf801C0Rec
    }
    public MSF801_CD_801Rec getMSF801_CD (AABPAYM8pCommonData commonData,String sDeductionCode){
        info("getMSF801_CD",getClass())
        MSF801_CD_801Rec msf801CDRec = new MSF801_CD_801Rec()


        if (sDeductionCode.trim() == ""){
            info("Input not complete for getMSF801_CD - DeductionCode:"+sDeductionCode,getClass())
            return msf801CDRec
        }

        Constraint c1 = MSF801_CD_801Key.cntlRecType.equalTo("CD")
        Constraint c2 = MSF801_CD_801Key.cntlKeyRest.equalTo(commonData.getPayGroup()+sDeductionCode)
        def query = new QueryImpl (MSF801_CD_801Rec.class).and(c1).and(c2)

        try{
            msf801CDRec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf801CDRec == null){

            //* try to use global pay group

            c1 = MSF801_CD_801Key.cntlRecType.equalTo("CD")
            c2 = MSF801_CD_801Key.cntlKeyRest.equalTo("***"+sDeductionCode)

            query = new QueryImpl (MSF801_CD_801Rec.class).and(c1).and(c2)

            try{
                msf801CDRec = edoi.firstRow(query)
            }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
                info(e.getMessage(),getClass())
            }

            if (msf801CDRec == null){
                info("Unexpected value msf801CDRec=null",getClass())
                msf801CDRec = new MSF801_CD_801Rec()
            }
        }

        return msf801CDRec
    }

    public MSF801_PG_801Rec getMSF801_PG (AABPAYM8pCommonData commonData){
        info("getMSF801_PG",getClass())

        MSF801_PG_801Rec msf801PGRec = new MSF801_PG_801Rec ()
        Constraint c1 = MSF801_PG_801Key.cntlRecType.equalTo("PG")
        Constraint c2 = MSF801_PG_801Key.cntlKeyRest.equalTo(commonData.getPayGroup())
        def query = new QueryImpl(MSF801_PG_801Rec.class).and(c1).and(c2)

        try{
            msf801PGRec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
            msf801PGRec = new MSF801_PG_801Rec ()
        }

        if (msf801PGRec == null){
            info("Unexpected value msf801PGRec=null",getClass())
            msf801PGRec = new MSF801_PG_801Rec()
        }

        return msf801PGRec
    }

    public MSF80ARec getMSF80ARec (AABPAYM8pCommonData commonData, String sEarningCode){
        info("getMSF80ARec",getClass())

        MSF80ARec msf80aRec = new MSF80ARec()

        if (sEarningCode.trim() == ""){
            info("Input not complete for getMSF80ARec - EarningCode:"+sEarningCode,getClass())
            return msf80aRec
        }

        Constraint c1 = MSF80AKey.payGroupA.equalTo(commonData.getPayGroup())
        Constraint c2 = MSF80AKey.earnCodeA.equalTo(sEarningCode)
        def query = new QueryImpl(MSF80ARec.class).and(c1).and(c2)

        try{
            msf80aRec = this.edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf80aRec == null){
            info("Unexpected value msf80aRec=null",getClass())
            msf80aRec = new MSF80ARec()
        }


        return msf80aRec
    }

    public MSF801_A_801Rec getMSF801ARec (AABPAYM8pCommonData commonData, String sEarningCode, boolean globalPayGroup){
        info("getMSF801ARec",getClass())

        MSF801_A_801Rec msf801aRec = new MSF801_A_801Rec()

        if (sEarningCode.trim() == ""){
            info("Input not complete for getMSF801ARec - EarningCode:"+sEarningCode,getClass())
            return msf801aRec
        }
        String sPayGroup

        if (globalPayGroup){
            info("Use global Paygroup",getClass())
            sPayGroup = '***'
        }else{
            sPayGroup = commonData.getPayGroup()
        }
        String sCntlKeyRest = sPayGroup + sEarningCode
        Constraint c1 = MSF801_A_801Key.cntlKeyRest.equalTo(sCntlKeyRest)
        def query = new QueryImpl(MSF801_A_801Rec.class).and(c1)

        try{
            msf801aRec = this.edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf801aRec == null){
            info("Unexpected value msf80aRec=null",getClass())
            msf801aRec = new MSF801_A_801Rec()
        }


        return msf801aRec
    }

    public String getSupplierNo (AABPAYM8pCommonData commonData, String sDeductionCode){
        String supplierNo = ""
        String sZero = "00000000"
        info("getSupplierNo",getClass())

        //Read MSF822
        MSF822Rec msf822Rec = new MSF822Rec()

        String conYTDTots
        String YES_STRING = "Y"


        if (commonData.getConsYTDTots().equals(YES_STRING)){
            conYTDTots = "   "
        }else{
            conYTDTots = commonData.getPayGroup()
        }

        Constraint c1 = MSF822Key.employeeId.equalTo(commonData.getEmpId())
        Constraint c2 = MSF822Key.consPayGrp.equalTo(conYTDTots)
        Constraint c3 = MSF822Key.dednCode.equalTo(sDeductionCode)
        Constraint c4 = MSF822Rec.startDate.lessThan(commonData.getPeriodStartDate())
        Constraint c5 = MSF822Rec.endDate.greaterThan(commonData.getPeriodStartDate())
        Constraint c6 = MSF822Rec.endDate.equalTo(sZero)
        def query = new QueryImpl (MSF822Rec.class).and(c1).and(c2).and(c3).and(c4).and(c5.or(c6))

        try{
            msf822Rec = edoi.firstRow(query)
        }catch(com.mincom.ellipse.edoi.common.exception.EDOIObjectNotFoundException e){
            info(e.getMessage(),getClass())
        }

        if (msf822Rec == null){
            // No entry in MSF822
            info("Unexpected value msf820Rec=null",getClass())
            msf822Rec = new MSF822Rec()
        }

        if (msf822Rec.getSupplierNo().trim()!=""){
            supplierNo =  msf822Rec.getSupplierNo()
        }else {
            // If it could not found the supplier no from msf822
            // next read MSF081_CD
            info("Find supplier from MSf801CD",getClass())

            MSF801_CD_801Rec msf801CDRec = new MSF801_CD_801Rec()

            msf801CDRec = getMSF801_CD(commonData,sDeductionCode)

            if (msf801CDRec.getSupplierNoCd().trim()!=""){
                supplierNo = msf801CDRec.getSupplierNoCd()
            }
        }

        return supplierNo
    }

    public MSF880Rec getMSF880Rec (AABPAYM8pCommonData commonData,String leaveType){
        info("getMSF880Rec LeaveType:" + leaveType ,getClass())
        Constraint c1 = MSF880Key.employeeId.equalTo(commonData.getEmpId())
        Constraint c2 = MSF880Key.leaveType.equalTo(leaveType)
        def query = new QueryImpl(MSF880Rec.class).and(c1).and(c2)
        MSF880Rec msf880Rec = this.edoi.firstRow(query)

        if (msf880Rec == null){
            return new MSF880Rec()
        }else{
            return msf880Rec
        }
    }

    public MSF801_R_801Rec getMSF801_R(AABPAYM8pCommonData commonData, String cntlKeyRest ){
        info("getMSF801_R  cntlKeyRest:" + cntlKeyRest,getClass())
        Constraint c1 = MSF801_R_801Key.cntlRecType.equalTo("R")
        Constraint c2 = MSF801_R_801Key.cntlKeyRest.equalTo(cntlKeyRest)
        def query = new QueryImpl(MSF801_R_801Rec.class).and(c1).and(c2)
        MSF801_R_801Rec msf801R801Rec = this.edoi.firstRow(query)

        if (msf801R801Rec == null){
            return new MSF801_R_801Rec()
        }else{
            return msf801R801Rec
        }
    }

    public AABPAY_YTD getEMP_YTD (AABPAYM8pCommonData commonData, String sCode, String sSwitchCode){

        String YES_STRING = "Y"
        String DEDUCTION_CODE = "D"
        String EARNING_CODE = "A"
        String conYTDTots

        Constraint c1
        Constraint c2
        Constraint c3

        BigDecimal iTaxPtdUnits = 0
        BigDecimal iTaxPtdAmtL = 0
        BigDecimal iFisPtdUnits = 0
        BigDecimal iFisPtdAmtL = 0
        BigDecimal iCalPtdUnits = 0
        BigDecimal iCalPtdAmtL = 0
        BigDecimal iQtrPtdUnits = 0
        BigDecimal iQtyPtdAmtL = 0
        BigDecimal iMthPtdUnits = 0
        BigDecimal iMthPtdAmtL = 0

        info("getEMP_YTD",getClass())
        info ("WX_CONS_YTD_TOTS:"+commonData.getConsYTDTots(),getClass())

        if (commonData.getConsYTDTots().equals(YES_STRING)){
            conYTDTots = "   "
        }else{
            conYTDTots = commonData.getPayGroup()
        }

        if (sSwitchCode.equals(EARNING_CODE)){
            c1 = MSF823Key.consPayGrp.equalTo(conYTDTots)
            c2 = MSF823Key.employeeId.equalTo(commonData.getEmpId())
            c3 = MSF823Key.earnCode.equalTo(sCode)

            def query = new QueryImpl(MSF823Rec.class).and(c1).and(c2).and(c3).orderBy(MSF823Rec.msf823Key)

            this.edoi.search(query,this.maxRecordRead, {MSF823Rec msf823rec ->
                iTaxPtdUnits = iTaxPtdUnits + msf823rec.getCurTaxUnits()
                iTaxPtdAmtL = iTaxPtdAmtL + msf823rec.getCurTaxAmtL()
                iFisPtdUnits = iFisPtdUnits + msf823rec.getCurFisUnits()
                iFisPtdAmtL = iFisPtdAmtL + msf823rec.getCurFisAmtL()
                iCalPtdUnits = iCalPtdUnits + msf823rec.getCurCalUnits()
                iCalPtdAmtL = iCalPtdAmtL + msf823rec.getCurCalAmtL()
                iQtrPtdUnits = iQtrPtdUnits + msf823rec.getCurQtrUnits()
                iQtyPtdAmtL = iQtyPtdAmtL + msf823rec.getCurQtrAmtL()
                iMthPtdUnits = iMthPtdUnits + msf823rec.getCurMthUnits()
                iMthPtdAmtL = iMthPtdAmtL + msf823rec.getCurMthAmtL()
            })

        }else{

            c1 = MSF837Key.consPayGrp.equalTo(conYTDTots)
            c2 = MSF837Key.employeeId.equalTo(commonData.getEmpId())
            c3 = MSF837Key.dednCode.equalTo(sCode)

            def query = new QueryImpl(MSF837Rec.class).and(c1).and(c2).and(c3).orderBy(MSF837Rec.msf837Key)

            this.edoi.search(query,this.maxRecordRead, {MSF837Rec msf837rec ->
                iTaxPtdUnits = iTaxPtdUnits + msf837rec.getCurTaxUnits()
                iTaxPtdAmtL = iTaxPtdAmtL + msf837rec.getCurTaxAmtL()
                iFisPtdUnits = iFisPtdUnits + msf837rec.getCurFisUnits()
                iFisPtdAmtL = iFisPtdAmtL + msf837rec.getCurFisAmtL()
                iCalPtdUnits = iCalPtdUnits + msf837rec.getCurCalUnits()
                iCalPtdAmtL = iCalPtdAmtL + msf837rec.getCurCalAmtL()
                iQtrPtdUnits = iQtrPtdUnits + msf837rec.getCurQtrUnits()
                iQtyPtdAmtL = iQtyPtdAmtL + msf837rec.getCurQtrAmtL()
                iMthPtdUnits = iMthPtdUnits + msf837rec.getCurMthUnits()
                iMthPtdAmtL = iMthPtdAmtL + msf837rec.getCurMthAmtL()
            })


        }

        AABPAY_YTD aabPayYtd = new AABPAY_YTD()
        aabPayYtd.setTaxPtdUnits(iTaxPtdUnits)
        aabPayYtd.setTaxPtdAmtL(iTaxPtdAmtL)
        aabPayYtd.setFisPtdUnits(iFisPtdUnits)
        aabPayYtd.setFisPtdAmtL(iFisPtdAmtL)
        aabPayYtd.setCalPtdUnits(iCalPtdUnits)
        aabPayYtd.setCalPtdAmtL(iCalPtdAmtL)
        aabPayYtd.setQtrPtdUnits(iQtrPtdUnits)
        aabPayYtd.setQtyPtdAmtL(iQtyPtdAmtL)
        aabPayYtd.setMthPtdUnits(iMthPtdUnits)
        aabPayYtd.setMthPtdAmtL(iMthPtdAmtL)

        return aabPayYtd
    }

    public ArrayList<String> constructInputList(String sInputString){

        ArrayList outputArray = null
        String A_RECORD = "A"
        String B_RECORD = "B"
        String D_RECORD = "D"
        String F_RECORD = "F"
        String H_RECORD = "H"
        String J_RECORD = "J"
        String L_RECORD = "L"
        String N_RECORD = "N"

        info("String Length:" + sInputString.length().toString(),getClass() )
        String sRecordType = sInputString.substring(76,77)
        info("Construct Record Type:" + sRecordType,getClass() )

        switch (sRecordType){

            case A_RECORD:
                outputArray = [
                    sInputString.substring(0,3),
                    sInputString.substring(4,6),
                    sInputString.substring(7,10),
                    sInputString.substring(11,16),
                    sInputString.substring(17,18),
                    sInputString.substring(19,49),
                    sInputString.substring(50,62),
                    sInputString.substring(63,64),
                    sInputString.substring(65,75),
                    sInputString.substring(76,77),
                    sInputString.substring(78,128),
                    sInputString.substring(129,132),
                    sInputString.substring(133,141),
                    sInputString.substring(142,150),
                    sInputString.substring(151,181),
                    sInputString.substring(182,194),
                    sInputString.substring(195,207),
                    sInputString.substring(208,218),
                    sInputString.substring(219,220),
                    sInputString.substring(221,222),
                    sInputString.substring(223,255),
                    sInputString.substring(256,288),
                    sInputString.substring(289,321),
                    sInputString.substring(322,324),
                    sInputString.substring(325,329),
                    sInputString.substring(330,334),
                    sInputString.substring(335,385),
                    sInputString.substring(386,390),
                    sInputString.substring(391,441),
                    sInputString.substring(442,446),
                    sInputString.substring(447,497),
                    sInputString.substring(498,502),
                    sInputString.substring(503,553),
                    sInputString.substring(554,558),
                    sInputString.substring(559,609),
                    sInputString.substring(610,614),
                    sInputString.substring(615,665),
                    sInputString.substring(666,670),
                    sInputString.substring(671,721),
                    sInputString.substring(722,726),
                    sInputString.substring(727,777),
                    sInputString.substring(778,782),
                    sInputString.substring(783,833),
                    sInputString.substring(834,838),
                    sInputString.substring(839,889),
                    sInputString.substring(890,940),
                    sInputString.substring(941,948),
                    sInputString.substring(949,991),
                    sInputString.substring(992,1002),
                    sInputString.substring(1003,1043),
                    sInputString.substring(1044,1094),
                    sInputString.substring(1095,1101),
                    sInputString.substring(1102,1152),
                    sInputString.substring(1153,1169),
                    sInputString.substring(1170,1186),
                    sInputString.substring(1187,1203),
                    sInputString.substring(1204,1220),
                    sInputString.substring(1221,1237),
                    sInputString.substring(1238,1254),
                    sInputString.substring(1255,1258),
                    sInputString.substring(1259,1260),
                    sInputString.substring(1261,1262),
                    sInputString.substring(1263,1271),
                    sInputString.substring(1272,1273)
                ]

                break

            case B_RECORD:
                outputArray = [
                    sInputString.substring(0,3),
                    sInputString.substring(4,6),
                    sInputString.substring(7,10),
                    sInputString.substring(11,16),
                    sInputString.substring(17,18),
                    sInputString.substring(19,49),
                    sInputString.substring(50,62),
                    sInputString.substring(63,64),
                    sInputString.substring(65,75),
                    sInputString.substring(76,77),
                    sInputString.substring(78,158)
                ]

                break

            case D_RECORD:
                outputArray = [
                    sInputString.substring(0,3),
                    sInputString.substring(4,6),
                    sInputString.substring(7,10),
                    sInputString.substring(11,16),
                    sInputString.substring(17,18),
                    sInputString.substring(19,49),
                    sInputString.substring(50,62),
                    sInputString.substring(63,64),
                    sInputString.substring(65,75),
                    sInputString.substring(76,77),
                    sInputString.substring(78,79),
                    sInputString.substring(80,130),
                    sInputString.substring(131,133),
                    sInputString.substring(134,146),
                    sInputString.substring(147,159),
                    sInputString.substring(160,172),
                    sInputString.substring(173,174)
                ]

                break

            case F_RECORD:
                outputArray = [
                    sInputString.substring(0,3),
                    sInputString.substring(4,6),
                    sInputString.substring(7,10),
                    sInputString.substring(11,16),
                    sInputString.substring(17,18),
                    sInputString.substring(19,49),
                    sInputString.substring(50,62),
                    sInputString.substring(63,64),
                    sInputString.substring(65,75),
                    sInputString.substring(76,77),
                    sInputString.substring(78,93),
                    sInputString.substring(94,144),
                    sInputString.substring(145,165),
                    sInputString.substring(166,182),
                    sInputString.substring(183,184),
                    sInputString.substring(185,186),
                    sInputString.substring(187,188),
                    sInputString.substring(189,195)
                ]


                break

            case H_RECORD:
                outputArray = [
                    sInputString.substring(0,3),
                    sInputString.substring(4,6),
                    sInputString.substring(7,10),
                    sInputString.substring(11,16),
                    sInputString.substring(17,18),
                    sInputString.substring(19,49),
                    sInputString.substring(50,62),
                    sInputString.substring(63,64),
                    sInputString.substring(65,75),
                    sInputString.substring(76,77),
                    sInputString.substring(78,94),
                    sInputString.substring(95,111),
                    sInputString.substring(112,128),
                    sInputString.substring(129,145),
                    sInputString.substring(146,162),
                    sInputString.substring(163,179),
                    sInputString.substring(180,200),
                    sInputString.substring(201,217),
                    sInputString.substring(218,234),
                    sInputString.substring(235,251),
                    sInputString.substring(252,268),
                    sInputString.substring(269,285),
                    sInputString.substring(286,294),
                    sInputString.substring(295,335),
                    sInputString.substring(336,347)
                ]
                break

            case J_RECORD:
                outputArray = [
                    sInputString.substring(0,3),
                    sInputString.substring(4,6),
                    sInputString.substring(7,10),
                    sInputString.substring(11,16),
                    sInputString.substring(17,18),
                    sInputString.substring(19,49),
                    sInputString.substring(50,62),
                    sInputString.substring(63,64),
                    sInputString.substring(65,75),
                    sInputString.substring(76,77),
                    sInputString.substring(78,81),
                    sInputString.substring(82,112),
                    sInputString.substring(113,125),
                    sInputString.substring(126,132),
                    sInputString.substring(133,145),
                    sInputString.substring(146,158),
                    sInputString.substring(159,173),
                    sInputString.substring(174,190),
                    sInputString.substring(191,207),
                    sInputString.substring(208,224),
                    sInputString.substring(225,241),
                    sInputString.substring(242,243),
                    sInputString.substring(244,258),
                    sInputString.substring(259,265),
                    sInputString.substring(266,282),
                    sInputString.substring(283,284),
                    sInputString.substring(285,293),
                    sInputString.substring(294,295)
                ]

                break

            case L_RECORD:
                outputArray =[
                    sInputString.substring(0,3),
                    sInputString.substring(4,6),
                    sInputString.substring(7,10),
                    sInputString.substring(11,16),
                    sInputString.substring(17,18),
                    sInputString.substring(19,49),
                    sInputString.substring(50,62),
                    sInputString.substring(63,64),
                    sInputString.substring(65,75),
                    sInputString.substring(76,77),
                    sInputString.substring(78,81),
                    sInputString.substring(82,112),
                    sInputString.substring(113,125),
                    sInputString.substring(126,138),
                    sInputString.substring(139,155),
                    sInputString.substring(156,170),
                    sInputString.substring(171,172),
                    sInputString.substring(173,187),
                    sInputString.substring(188,194),
                    sInputString.substring(195,211),
                    sInputString.substring(212,213),
                    sInputString.substring(214,222)
                ]
                break

            case N_RECORD:
                outputArray = [
                    sInputString.substring(0,3),
                    sInputString.substring(4,6),
                    sInputString.substring(7,10),
                    sInputString.substring(11,16),
                    sInputString.substring(17,18),
                    sInputString.substring(19,49),
                    sInputString.substring(50,62),
                    sInputString.substring(63,64),
                    sInputString.substring(65,75),
                    sInputString.substring(76,77),
                    sInputString.substring(78,81),
                    sInputString.substring(82,112),
                    sInputString.substring(113,125),
                    sInputString.substring(126,140),
                    sInputString.substring(141,157),
                    sInputString.substring(158,159),
                    sInputString.substring(160,162),
                    sInputString.substring(163,165),
                    sInputString.substring(166,168),
                    sInputString.substring(169,171),
                    sInputString.substring(172,174),
                    sInputString.substring(175,176),
                    sInputString.substring(177,178),
                    sInputString.substring(179,191),
                    sInputString.substring(192,193)
                ]
                break
            default:
                outputArray = sInputString.split(",")
                break
        }

        return outputArray;
    }
}

public class AABPAY_YTD{
    BigDecimal taxPtdUnits
    BigDecimal taxPtdAmtL
    BigDecimal fisPtdUnits
    BigDecimal fisPtdAmtL
    BigDecimal calPtdUnits
    BigDecimal calPtdAmtL
    BigDecimal qtrPtdUnits
    BigDecimal qtyPtdAmtL
    BigDecimal mthPtdUnits
    BigDecimal mthPtdAmtL
}

public class AABPAY_Counter{
    Integer iMSF8P1
    Integer iMSF8P2
    Integer iMSF8P3
    Integer iMSF8P4
    Integer iMSF8P5
    Integer iMSF8P6
    Integer iMSF8P7
    Integer iMSF8P8
    Integer iMSF8P9
    Integer iMSF8PA
    Integer iMSF8PB
    Integer iMSF8PC
    Integer iMSF8PD
    Integer iMSF8PE

    public void initCounter(){
        this.iMSF8P1 = 0
        this.iMSF8P2 = 0
        this.iMSF8P3 = 0
        this.iMSF8P4 = 0
        this.iMSF8P5 = 0
        this.iMSF8P6 = 0
        this.iMSF8P7 = 0
        this.iMSF8P8 = 0
        this.iMSF8P9 = 0
        this.iMSF8PA = 0
        this.iMSF8PB = 0
        this.iMSF8PC = 0
        this.iMSF8PD = 0
        this.iMSF8PE = 0

    }
}

public class AABPAYReportElement{
    AABPAY_Counter msfCounter
    BigDecimal totalLineProcessed
    List <String> errorLine = new ArrayList <String>()
}

/*run script*/  
ProcessAABPAY process = new ProcessAABPAY();
process.runBatch(binding);
