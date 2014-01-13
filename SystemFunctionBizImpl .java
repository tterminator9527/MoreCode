package biz.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;

import biz.SystemFunctionBiz;
import dao.SystemFunctionDao;
import dao.impl.BaseDaoImpl;
import dao.impl.SystemFunctionDaoImpl;
import entity.SystemFunction;

/**
 * 系统功能业务逻辑实现类
 * @author 	ls
 * @date	2012-06-08
 */
public class SystemFunctionBizImpl implements SystemFunctionBiz {
	private SystemFunctionDao systemFunctionDaoImpl = new SystemFunctionDaoImpl();
	
	/**
	 * 增加系统功能
	 * @author 	ls
	 * @date	2012-06-08
	 */
	public int addSystemFunction(SystemFunction systemFunction){
		return systemFunctionDaoImpl.addSystemFunction(systemFunction);
	}
	
	/**
	 * 删除系统功能
	 * @author 	ls
	 * @date	2012-06-08
	 */
	public int deleteSystemFunction(int sf_Id){
		return systemFunctionDaoImpl.deleteSystemFunction(sf_Id);
	}
	
	/**
	 * 修改系统功能
	 * @author 	ls
	 * @date	2012-06-08
	 */
	public int updateSystemFunction(SystemFunction systemFunction){
		return systemFunctionDaoImpl.updateSystemFunction(systemFunction);
	}
			
	/**
	 * 获取所有系统功能
	 * @author 	ls
	 * @date	2012-06-08
	 */
	public List getAllSystemFunction(){
		List list = new ArrayList();
		Result result = systemFunctionDaoImpl.getAllSystemFunction();
		if (null != result && 0 < result.getRowCount()){
			for (int i = 0; i < result.getRowCount(); i++){
				Map map = result.getRows()[i];
				SystemFunction systemFunction = new SystemFunction();
				systemFunction.setSf_Id((Integer)map.get("SF_Id"));
				systemFunction.setSf_Code((String)map.get("SF_Code"));
				systemFunction.setSf_Name((String)map.get("SF_Name"));
				systemFunction.setSf_Url((String)map.get("SF_Url"));
				systemFunction.setSf_sort((Integer)map.get("SF_Sort"));
				systemFunction.setSf_Remarks((String)map.get("SF_Remarks"));	
				systemFunction.setSf_PName(map.get("SF_PName")==null?"无":map.get("SF_PName").toString());
				list.add(systemFunction);
			}
		}
		return list;
	}

	/**
	 * 根据SF_Id查询系统功能的详细信息
	 * @author 	ls
	 * @date	2012-06-08
	 */
	public SystemFunction getSystemFunctionBySF_Id(int sf_Id){
		SystemFunction systemFunction = new SystemFunction();
		Result result = systemFunctionDaoImpl.getSystemFunctionBySF_Id(sf_Id);
		if (null != result && 0 < result.getRowCount()){
			for (int i = 0; i < result.getRowCount(); i++){
				Map map = result.getRows()[i];
				systemFunction.setSf_Id((Integer)map.get("SF_Id"));
				systemFunction.setSf_Code((String)map.get("SF_Code"));
				systemFunction.setSf_Name((String)map.get("SF_Name"));
				systemFunction.setSf_SF_Id((Integer)map.get("SF_SF_Id"));
				systemFunction.setSf_Url((String)map.get("SF_Url"));
				systemFunction.setSf_sort((Integer)map.get("SF_Sort"));
				systemFunction.setSf_Remarks((String)map.get("SF_Remarks"));
				systemFunction.setSf_PName(map.get("SF_PName")==null?"无":map.get("SF_PName").toString());
			}
		}
		return systemFunction;
	}
	
	/**
	 * 获取所有根系统功能编号及名称
	 * @author 	ls
	 * @date	2012-06-12
	 */
	public List getRootSystemFunction(){
		List list = new ArrayList();
		Result result = systemFunctionDaoImpl.getRootSystemFunction();
		if (null != result && 0 < result.getRowCount()){
			for (int i = 0; i < result.getRowCount(); i++){
				Map map = result.getRows()[i];
				SystemFunction systemFunction = new SystemFunction();
				systemFunction.setSf_Id((Integer)map.get("SF_Id"));
				systemFunction.setSf_Name((String)map.get("SF_Name"));
				list.add(systemFunction);
			}
		}
		return list;
	}
	
	/**
	 * 获取所有二级系统功能信息
	 * @author 	ls
	 * @date	2012-06-13
	 */
	public List getTwoSystemFunction(){
		List list = new ArrayList();
		Result result = systemFunctionDaoImpl.getTwoSystemFunction();
		if (null != result && 0 < result.getRowCount()){
			for (int i = 0; i < result.getRowCount(); i++){
				Map map = result.getRows()[i];
				SystemFunction systemFunction = new SystemFunction();
				systemFunction.setSf_Id((Integer)map.get("SF_Id"));
				systemFunction.setSf_Code((String)map.get("SF_Code"));
				systemFunction.setSf_Name((String)map.get("SF_Name"));
				systemFunction.setSf_Remarks((String)map.get("SF_Remarks"));
				systemFunction.setSf_PName(map.get("SF_PName")==null?"无":map.get("SF_PName").toString());
				list.add(systemFunction);
			}
		}
		return list;
	}
	
	/**
	 * 根据Su_Id获取该用户所能执行的功能
	 * @author 	ls
	 * @date	2012-07-12
	 */
	public List getSystemFunctionBySu_Id(int su_Id){
		List list = new ArrayList();
		// 获取所有一级功能
		Result aSystemFunctionResult = systemFunctionDaoImpl.getOneSystemFunction();
		Result userFunctionresult = systemFunctionDaoImpl.getSystemFunctionBySu_Id(su_Id);
		if (null != aSystemFunctionResult && 0 < aSystemFunctionResult.getRowCount()){
			for (int i = 0; i < aSystemFunctionResult.getRowCount(); i++){
				String sf_Name = (aSystemFunctionResult.getRows()[i]).get("SF_Name").toString();
				List systemFunctionList = new ArrayList();
				if (null != userFunctionresult && 0 < userFunctionresult.getRowCount()){
					for (int j = 0; j < userFunctionresult.getRowCount(); j++){
						Map map = userFunctionresult.getRows()[j];
						SystemFunction systemFunction = new SystemFunction();
						String sf_PName = map.get("SF_PName").toString();
						systemFunction.setSf_PName(map.get("SF_PName").toString());
						systemFunction.setSf_Id((Integer)map.get("SF_Id"));
						systemFunction.setSf_Code((String)map.get("SF_Code"));
						systemFunction.setSf_Name((String)map.get("SF_Name"));
						systemFunction.setSf_Url((String)map.get("SF_URL"));
						if ((map.get("SF_PName").toString()).equals(sf_Name))
							systemFunctionList.add(systemFunction);
					}
				}
				Map map = new HashMap();
				if (null != systemFunctionList && 0 < systemFunctionList.size()){
					map.put("systemFunctionList", systemFunctionList);
					map.put("pName", sf_Name);
					list.add(map);
				}
			}
		}
		return list;
	}
}
