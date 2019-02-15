package com.post.im.tbimapplyapprbiz;
import java.io.*;
import java.util.*;
import java.math.*;
import commonj.sdo.DataObject;
import com.primeton.engine.core.impl.*;
import com.primeton.engine.*;
import com.primeton.ext.engine.component.Operation;
import com.primeton.ext.engine.component.asyn.AsynInvokeBiz;
import com.primeton.ext.engine.component.asyn.AsynInvokeService;
import com.eos.engine.component.ILogicComponent;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.eos.common.transaction.ITransactionDefinition;
import com.primeton.ext.data.sdo.DataUtil;
import com.eos.data.datacontext.DataContextManager;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.asyn.AsynMethodTemplateFactory;
import com.eos.common.asyn.AsynMethodTemplate;
import com.primeton.engine.core.impl.helper.DataBuilder;
import com.eos.runtime.resource.ContributionMetaData;
import com.primeton.ext.runtime.resource.ContributionMetaDataManager;
import com.primeton.engine.core.impl.context.RuntimeContext;
import com.primeton.ext.engine.core.ExceptionConstant;
import com.primeton.engine.core.exception.EOSEngineRuntimeException;
import com.primeton.ext.engine.core.ExceptionConstant;
import com.eos.access.client.ServiceContext;
import com.eos.sca.host.IServiceInvoker;
import com.eos.engine.core.ValidateModel;
import com.eos.engine.core.ValidateResult;
import com.eos.engine.core.mbean.EngineConfigHandler;
import com.eos.engine.core.mbean.EngineConfigModel;
import com.primeton.ext.common.logging.LoggerFactory;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.logging.Logger;
import com.primeton.ext.common.logging.SysLogger;
import com.eos.common.statistic.StatisticManager;
import com.eos.common.statistic.StatisticMessage;
import com.primeton.ext.common.statistic.BizflowStatisticMessage;
import com.primeton.ext.common.statistic.PageflowStatisticMessage;
import com.primeton.ext.common.statistic.ServiceStatisticMessage;
import com.primeton.ext.common.statistic.SQLStatisticMessage;
import com.eos.data.datacontext.ISessionMap;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.data.datacontext.IUserObject;
import com.eos.system.ServerContext;
import com.primeton.ext.engine.core.ValidateMessage;
import com.primeton.ext.system.utility.international.ResourceMessageUtil;
import com.eos.data.datacontext.IMUODataContext;
import com.primeton.ext.common.muo.MUODataContextHelper;
import com.eos.data.datacontext.IMapContextFactory;
public class addTbImApplyAppr_biz extends com.primeton.engine.core.impl.AbstractJavaBL{
com.eos.data.datacontext.IDataContext sessionContext = null;
Object[] __results = new Object[0];;
public addTbImApplyAppr_biz(){
}
public addTbImApplyAppr_biz(java.util.Map head,java.util.Map attachment){
super(head,attachment);
}
public String getQName(){
  return "com.post.im.tbimapplyapprbiz.addTbImApplyAppr.biz";
}
public String getShortName(){
  return "addTbImApplyAppr.bizx";
}
public List<Activity> getActivities(){
  if(this._eosInternal.activities==null){
    this._eosInternal.activities = new ArrayList<Activity>();
    this._eosInternal.activities.add(new Activity("start0","开始","start"));
    this._eosInternal.activities.add(new Activity("end0","结束","end"));
    this._eosInternal.activities.add(new Activity("invokePojo0","insertEntity","invoke"));
    this._eosInternal.activities.add(new Activity("invokePojo1","getPrimaryKey","invoke"));
  }
  return this._eosInternal.activities;
}
public String getProcessName(){
  return "";
}
public boolean isAccessOutTransaction(){
  return true;
}
public Variable[] getVariables(String category){
  Variable[]variables = null;
  if(category.equals(Operation.BIZ_INPUT_VARIBLE)){
      variables = (Variable[])this._eosInternal.getAttrs().get(Operation.BIZ_INPUT_VARIBLE);
  }else{
      variables = (Variable[])this._eosInternal.getAttrs().get(Operation.BIZ_OUTPUT_VARIBLE);
  }
  if(variables == null){
      Variable[]__inputVariables = new Variable[1];
      __inputVariables[0] = new Variable();
      __inputVariables[0].setArray(false);
      __inputVariables[0].setCategory(3);
      __inputVariables[0].setDataObjectType("com.post.im.resourceM.appr.appr.TbImApplyAppr");
      __inputVariables[0].setName("tbimapplyappr");
      __inputVariables[0].setTypeClass(commonj.sdo.DataObject.class);
      __inputVariables[0].setTypeName("commonj.sdo.DataObject");

      Variable[]__outputVariables = new Variable[0];
      this._eosInternal.getAttrs().put(Operation.BIZ_INPUT_VARIBLE, __inputVariables);
      this._eosInternal.getAttrs().put(Operation.BIZ_OUTPUT_VARIBLE, __outputVariables);
      variables = (Variable[])this._eosInternal.getAttrs().get(category);
   }
   return variables;
}
private int start0(RuntimeContext __context,commonj.sdo.DataObject tbimapplyappr) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("start0");
__context.setCurrentActivityName("开始");
__context.setCurrentActivityType("start");
__context.setCurrentActivityValue(null);
this.beforeHandlers("start0",__context);
  __eosCurrentActivityID=70001;
  this.afterHandlers("start0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("start0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=开始][activity id=start0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("start0",__context);
}
}
private int end0(RuntimeContext __context,commonj.sdo.DataObject tbimapplyappr) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("end0");
__context.setCurrentActivityName("结束");
__context.setCurrentActivityType("end");
__context.setCurrentActivityValue(null);
this.beforeHandlers("end0",__context);
  __eosCurrentActivityID=-1;
  this.afterHandlers("end0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("end0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=结束][activity id=end0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("end0",__context);
}
}
private int invokePojo0(RuntimeContext __context,commonj.sdo.DataObject tbimapplyappr) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokePojo0");
__context.setCurrentActivityName("insertEntity");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue("com.eos.foundation.database.DatabaseUtil.insertEntity");
this.beforeHandlers("invokePojo0",__context);
long __startTime=System.currentTimeMillis();
try{
  __resumeException();
   if(!__context.getAttributes().containsKey("invokePojo0__suspend")){
com.eos.foundation.database.DatabaseUtil.insertEntity("default",tbimapplyappr);//pojo:invokePojo0//
if(isSuspend()){
return 70000;
}
}else{
      __context.getAttributes().remove("invokePojo0__suspend");
}
}finally{
 if(EngineConfigHandler.getEngineConfigModel().getLogPojoWhenTimeout() >= 0 ){
  long __executeTime = System.currentTimeMillis() - __startTime;
  if (__executeTime >= EngineConfigHandler.getEngineConfigModel().getLogPojoWhenTimeout()) {
    com.primeton.ext.common.logging.LoggerFactory.getPojoSysLogger().perform("com.eos.foundation.database.DatabaseUtil.insertEntity", String.valueOf(__executeTime));
  }
 }
}
  __eosCurrentActivityID=40000;
  this.afterHandlers("invokePojo0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokePojo0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=insertEntity][activity id=invokePojo0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokePojo0",__context);
}
}
private int invokePojo1(RuntimeContext __context,commonj.sdo.DataObject tbimapplyappr) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokePojo1");
__context.setCurrentActivityName("getPrimaryKey");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue("com.eos.foundation.database.DatabaseExt.getPrimaryKey");
this.beforeHandlers("invokePojo1",__context);
long __startTime=System.currentTimeMillis();
try{
  __resumeException();
   if(!__context.getAttributes().containsKey("invokePojo1__suspend")){
com.eos.foundation.database.DatabaseExt.getPrimaryKey(tbimapplyappr);//pojo:invokePojo1//
if(isSuspend()){
return 70001;
}
}else{
      __context.getAttributes().remove("invokePojo1__suspend");
}
}finally{
 if(EngineConfigHandler.getEngineConfigModel().getLogPojoWhenTimeout() >= 0 ){
  long __executeTime = System.currentTimeMillis() - __startTime;
  if (__executeTime >= EngineConfigHandler.getEngineConfigModel().getLogPojoWhenTimeout()) {
    com.primeton.ext.common.logging.LoggerFactory.getPojoSysLogger().perform("com.eos.foundation.database.DatabaseExt.getPrimaryKey", String.valueOf(__executeTime));
  }
 }
}
  __eosCurrentActivityID=70000;
  this.afterHandlers("invokePojo1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokePojo1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=getPrimaryKey][activity id=invokePojo1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokePojo1",__context);
}
}
public Object[] _invoke(commonj.sdo.DataObject tbimapplyappr) throws Throwable{
       if(isSuspend()){
           return null;
       }
  __context.set("tbimapplyappr",tbimapplyappr);
  __invokeBiz();
  return __results;
}
public Object[] invoke(Object[] __params) throws Throwable{
Logger tracelog = TraceLoggerFactory.getLogger(this.getQName());
Variable[] variables = this.getVariables(Operation.BIZ_INPUT_VARIBLE);
if (__params != null) {
    for(int i = 0; i < __params.length; i++) {
        if(__params[i] == null) {
            tracelog.warn("Warning: [biz={0}][param={1}] is null.", new Object[]{this.getQName(), variables[i].getName()});
        }
    }
}
SysLogger log = LoggerFactory.getLogicflowSysLogger();
com.primeton.ext.common.logging.EngineLogger engineLog = null;
sessionContext = DataContextManager.current().getMUODataContext();
engineLog = LoggerFactory.getBizlogicCurrentEngineLogger();
try{
this.__context.setName(this.getQName());
this.__context.setProcessName(this.getProcessName());
this.beforeHandlers(null,__context);
IUserObject __userObject = null;if(sessionContext != null)__userObject = ((com.eos.data.datacontext.IMUODataContext)sessionContext).getUserObject();String __remoteIP = null;if(__userObject != null) __remoteIP = __userObject.getUserRemoteIP();com.primeton.ext.common.statistic.BizflowStatisticMessage __item=new com.primeton.ext.common.statistic.BizflowStatisticMessage(this.getQName(),DataContextManager.current().getContextStack().getUniqueID(),__remoteIP);
if(__userObject != null) __item.setUserId(__userObject.getUserId());
com.eos.common.statistic.StatisticManager.push(__item);
int __paramDefineSize = 1;
if(__params == null || __params.length < __paramDefineSize){
throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100063, new Object[]{this.getQName()});
}
commonj.sdo.DataObject tbimapplyappr;
try{
 tbimapplyappr = (commonj.sdo.DataObject)__params[0];
if(__params[0]==null){__context.addTypeMapping("xpath:/tbimapplyappr","sdo:com.post.im.resourceM.appr.appr.TbImApplyAppr");}
if(__params[0] != null){
  if(!(__params[0] instanceof commonj.sdo.DataObject)){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100066,"Param Name:tbimapplyappr ,Input param object type: "+__params[0].getClass().getName() +" not match with define:commonj.sdo.DataObject:com.post.im.resourceM.appr.appr.TbImApplyAppr",new Object[]{this.getQName(),"tbimapplyappr",__params[0].getClass().getName(),"commonj.sdo.DataObject:com.post.im.resourceM.appr.appr.TbImApplyAppr"});
  }
try{
  if(!"com.post.im.resourceM.appr.appr.TbImApplyAppr".equals(((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName())){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100067,"Param Name:tbimapplyappr ,Input param object type: "+((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName()+" not match with define:commonj.sdo.DataObject:com.post.im.resourceM.appr.appr.TbImApplyAppr",new Object[]{this.getQName(),"tbimapplyappr",((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName(),"commonj.sdo.DataObject:com.post.im.resourceM.appr.appr.TbImApplyAppr"});
  }
 }
 catch(EOSEngineRuntimeException ___e){tracelog.warn(___e.getMessage());}}
}catch(java.lang.ClassCastException ce){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100066,"Param Name:tbimapplyappr ,Input param object type: "+__params[0].getClass().getName() +" not match with define:commonj.sdo.DataObject",new Object[]{this.getQName(),"tbimapplyappr",__params[0].getClass().getName(),"commonj.sdo.DataObject"});
}
log.entry();
engineLog.log("start", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context));
Object[] results =  _invoke(tbimapplyappr);//2//
this.afterHandlers(null,__context);
log.exit();
engineLog.log("end", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context));
if(results == null){
  results = new Object[0];
}
return results;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
  log.exception(__ex.getMessage());
  engineLog.log("exception", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context), __ex);
  throw __ex;
}catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers(null,__context,__ex);
  log.exception(__ex.getMessage());
  engineLog.log("exception", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context), __ex);
  throw __ex;
}finally{
com.eos.common.statistic.StatisticManager.pop();
	TransactionManagerProxy txProxy = this._eosInternal.getTxProxy();
	if(txProxy != null){
		if(!txProxy.isNestTx() && txProxy.getBeginCount() > 0){
			txProxy.forceRollback();
		}
	}
this.finallyHandlers(null,__context);
sessionContext = null;
}
}
public void __restoreField()  throws Throwable{
}
public void __invokeBiz()  throws Throwable{
int __eosCurrentActivityID= __context.getCursorId();
if(__eosCurrentActivityID==-10000){
__eosCurrentActivityID=10000;
}
  while(__eosCurrentActivityID!=0){       if(isSuspend()){           break;       }//1,2//
    switch(__eosCurrentActivityID){
    case 10000:
__eosCurrentActivityID = __choicestart0(__eosCurrentActivityID);//start0//
      break;
    case 40000:
__eosCurrentActivityID = __choiceend0(__eosCurrentActivityID);//end0//
      break;
    case 70000:
__eosCurrentActivityID = __choiceinvokePojo0(__eosCurrentActivityID);//invokePojo0//
      break;
    case 70001:
__eosCurrentActivityID = __choiceinvokePojo1(__eosCurrentActivityID);//invokePojo1//
      break;
    case -4:
      __eosCurrentActivityID= __breakLoop();
      break;
    case -5:
      __eosCurrentActivityID= __continueLoop();
      break;
    default: __eosCurrentActivityID=-1;
    callBack(__results);
  return;
  }
  }
}
private int __choicestart0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimapplyappr;
      tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
    try{
      __eosCurrentActivityID=start0(__context,tbimapplyappr);
      tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceend0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimapplyappr;
      tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
    try{
      __eosCurrentActivityID=end0(__context,tbimapplyappr);
      tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokePojo0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimapplyappr;
      tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
    try{
      __eosCurrentActivityID=invokePojo0(__context,tbimapplyappr);
      tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokePojo1(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimapplyappr;
      tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
    try{
      __eosCurrentActivityID=invokePojo1(__context,tbimapplyappr);
      tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
}
