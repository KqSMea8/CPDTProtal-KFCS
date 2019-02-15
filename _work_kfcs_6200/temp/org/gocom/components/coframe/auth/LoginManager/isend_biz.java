package org.gocom.components.coframe.auth.LoginManager;
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
public class isend_biz extends com.primeton.engine.core.impl.AbstractJavaBL{
com.eos.data.datacontext.IDataContext sessionContext = null;
Object[] __results = new Object[1];;
java.util.Date today;
int isend;
public isend_biz(){
}
public isend_biz(java.util.Map head,java.util.Map attachment){
super(head,attachment);
}
public String getQName(){
  return "org.gocom.components.coframe.auth.LoginManager.isend.biz";
}
public String getShortName(){
  return "isend.bizx";
}
public List<Activity> getActivities(){
  if(this._eosInternal.activities==null){
    this._eosInternal.activities = new ArrayList<Activity>();
    this._eosInternal.activities.add(new Activity("start0","开始","start"));
    this._eosInternal.activities.add(new Activity("end0","结束","end"));
    this._eosInternal.activities.add(new Activity("assign0","有失效日期时，则判断是否过期","assign"));
    this._eosInternal.activities.add(new Activity("assign1","没有失效日期，或未过期时","assign"));
    this._eosInternal.activities.add(new Activity("assign3","已过期","assign"));
    this._eosInternal.activities.add(new Activity("assign2","未到使用期","assign"));
    this._eosInternal.activities.add(new Activity("switch1","密码是否过期","switch"));
    this._eosInternal.activities.add(new Activity("assign4","密码过期","assign"));
    this._eosInternal.activities.add(new Activity("switch0","空操作","switch"));
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
      __inputVariables[0].setDataObjectType("org.gocom.components.coframe.rights.dataset.CapUser");
      __inputVariables[0].setName("capuser");
      __inputVariables[0].setTypeClass(commonj.sdo.DataObject.class);
      __inputVariables[0].setTypeName("commonj.sdo.DataObject");

      Variable[]__outputVariables = new Variable[1];
      __outputVariables[0] = new Variable();
      __outputVariables[0].setArray(false);
      __outputVariables[0].setCategory(1);
      __outputVariables[0].setName("isend");
      __outputVariables[0].setTypeClass(int.class);
      __outputVariables[0].setTypeName("int");
      this._eosInternal.getAttrs().put(Operation.BIZ_INPUT_VARIBLE, __inputVariables);
      this._eosInternal.getAttrs().put(Operation.BIZ_OUTPUT_VARIBLE, __outputVariables);
      variables = (Variable[])this._eosInternal.getAttrs().get(category);
   }
   return variables;
}
private int start0(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("start0");
__context.setCurrentActivityName("开始");
__context.setCurrentActivityType("start");
__context.setCurrentActivityValue(null);
this.beforeHandlers("start0",__context);
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capuser/enddate"), (Object)null ,"ISNULL")){
    __eosCurrentActivityID=20001;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=20000;
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
private int end0(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("end0");
__context.setCurrentActivityName("结束");
__context.setCurrentActivityType("end");
__context.setCurrentActivityValue(null);
this.beforeHandlers("end0",__context);
  __results = new Object[1];
  __results[0]=com.primeton.ext.data.sdo.DataUtil.toInt(this.isend);
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
private int assign0(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign0");
__context.setCurrentActivityName("有失效日期时，则判断是否过期");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign0",__context);
  __context.set("today",com.primeton.ext.data.sdo.DataUtil.toDate(new Date()));
  this.today = __context.getDate("today");
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capuser/enddate"),this.today,"LE")){
    __eosCurrentActivityID=20003;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=20001;
  this.afterHandlers("assign0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=有失效日期时，则判断是否过期][activity id=assign0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign0",__context);
}
}
private int assign1(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign1");
__context.setCurrentActivityName("没有失效日期，或未过期时");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign1",__context);
  __context.set("isend",Integer.valueOf("1").intValue());
  this.isend = __context.getInt("isend");
  __context.set("today",com.primeton.ext.data.sdo.DataUtil.toDate(new Date()));
  this.today = __context.getDate("today");
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capuser/startdate"), (Object)null ,"NOTNULL")){
    __eosCurrentActivityID=30000;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=30001;
  this.afterHandlers("assign1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=没有失效日期，或未过期时][activity id=assign1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign1",__context);
}
}
private int assign3(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign3");
__context.setCurrentActivityName("已过期");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign3",__context);
  __context.set("isend",Integer.valueOf("3").intValue());
  this.isend = __context.getInt("isend");
  __eosCurrentActivityID=40000;
  this.afterHandlers("assign3",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign3",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=已过期][activity id=assign3][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign3",__context);
}
}
private int assign2(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign2");
__context.setCurrentActivityName("未到使用期");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign2",__context);
  __context.set("isend",Integer.valueOf("4").intValue());
  this.isend = __context.getInt("isend");
  __eosCurrentActivityID=40000;
  this.afterHandlers("assign2",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign2",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=未到使用期][activity id=assign2][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign2",__context);
}
}
private int switch1(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("switch1");
__context.setCurrentActivityName("密码是否过期");
__context.setCurrentActivityType("switch");
__context.setCurrentActivityValue(null);
this.beforeHandlers("switch1",__context);
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capuser/invaldate"),this.today,"LE")){
    __eosCurrentActivityID=20004;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=40000;
  this.afterHandlers("switch1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("switch1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=密码是否过期][activity id=switch1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("switch1",__context);
}
}
private int assign4(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign4");
__context.setCurrentActivityName("密码过期");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign4",__context);
  __context.set("isend",Integer.valueOf("5").intValue());
  this.isend = __context.getInt("isend");
  __eosCurrentActivityID=40000;
  this.afterHandlers("assign4",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign4",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=密码过期][activity id=assign4][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign4",__context);
}
}
private int switch0(RuntimeContext __context,commonj.sdo.DataObject capuser) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("switch0");
__context.setCurrentActivityName("空操作");
__context.setCurrentActivityType("switch");
__context.setCurrentActivityValue(null);
this.beforeHandlers("switch0",__context);
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capuser/startdate"),this.today,"GE")){
    __eosCurrentActivityID=20002;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=30001;
  this.afterHandlers("switch0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("switch0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=空操作][activity id=switch0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("switch0",__context);
}
}
public Object[] _invoke(commonj.sdo.DataObject capuser) throws Throwable{
       if(isSuspend()){
           return null;
       }
  __context.set("capuser",capuser);
  __context.set("today",today);
  __context.set("isend",isend);
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
commonj.sdo.DataObject capuser;
try{
 capuser = (commonj.sdo.DataObject)__params[0];
if(__params[0]==null){__context.addTypeMapping("xpath:/capuser","sdo:org.gocom.components.coframe.rights.dataset.CapUser");}
if(__params[0] != null){
  if(!(__params[0] instanceof commonj.sdo.DataObject)){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100066,"Param Name:capuser ,Input param object type: "+__params[0].getClass().getName() +" not match with define:commonj.sdo.DataObject:org.gocom.components.coframe.rights.dataset.CapUser",new Object[]{this.getQName(),"capuser",__params[0].getClass().getName(),"commonj.sdo.DataObject:org.gocom.components.coframe.rights.dataset.CapUser"});
  }
try{
  if(!"org.gocom.components.coframe.rights.dataset.CapUser".equals(((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName())){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100067,"Param Name:capuser ,Input param object type: "+((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName()+" not match with define:commonj.sdo.DataObject:org.gocom.components.coframe.rights.dataset.CapUser",new Object[]{this.getQName(),"capuser",((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName(),"commonj.sdo.DataObject:org.gocom.components.coframe.rights.dataset.CapUser"});
  }
 }
 catch(EOSEngineRuntimeException ___e){tracelog.warn(___e.getMessage());}}
}catch(java.lang.ClassCastException ce){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100066,"Param Name:capuser ,Input param object type: "+__params[0].getClass().getName() +" not match with define:commonj.sdo.DataObject",new Object[]{this.getQName(),"capuser",__params[0].getClass().getName(),"commonj.sdo.DataObject"});
}
log.entry();
engineLog.log("start", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context));
Object[] results =  _invoke(capuser);//2//
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
      this.today=__context.getDate("today");
      this.isend=__context.getInt("isend");
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
    case 20000:
__eosCurrentActivityID = __choiceassign0(__eosCurrentActivityID);//assign0//
      break;
    case 20001:
__eosCurrentActivityID = __choiceassign1(__eosCurrentActivityID);//assign1//
      break;
    case 20003:
__eosCurrentActivityID = __choiceassign3(__eosCurrentActivityID);//assign3//
      break;
    case 20002:
__eosCurrentActivityID = __choiceassign2(__eosCurrentActivityID);//assign2//
      break;
    case 30001:
__eosCurrentActivityID = __choiceswitch1(__eosCurrentActivityID);//switch1//
      break;
    case 20004:
__eosCurrentActivityID = __choiceassign4(__eosCurrentActivityID);//assign4//
      break;
    case 30000:
__eosCurrentActivityID = __choiceswitch0(__eosCurrentActivityID);//switch0//
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
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=start0(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceend0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=end0(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceassign0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=assign0(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceassign1(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=assign1(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceassign3(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=assign3(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceassign2(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=assign2(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceswitch1(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=switch1(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceassign4(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=assign4(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceswitch0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject capuser;
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
    try{
      __eosCurrentActivityID=switch0(__context,capuser);
      capuser=(commonj.sdo.DataObject)__context.getDataObject("capuser");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
}
