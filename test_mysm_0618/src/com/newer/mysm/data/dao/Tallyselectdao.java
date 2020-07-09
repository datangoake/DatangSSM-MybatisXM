package com.newer.mysm.data.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;

import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.Tally;
import com.newer.mysm.data.util.TallyDTO;

public interface Tallyselectdao {
	
//	@Select("select * from tally")
//	@Options(flushCache = false, timeout = 10000,useCache=true)
//	@Results({ 
//		    @Result(id = true, property="tallyid", column="tallyid"),
//			@Result(property="type",column="type"),
//			@Result(property="price", column="price" ),
//			@Result(property="payDate", column="payDate" ),		
//	})
	
	@Select("<script>select * from"
            + "(select row_number() over(order by tallyid) no, t.* from tally t where 1=1"
			+"<if test='dto.search_tallyid != null'>"
			+"and t.tallyid = #{dto.search_tallyid}"
			+"</if>"
			+"<if test='dto.search_type != null'>"
			+"and t.type like '%'||#{dto.search_type}||'%'"
			+"</if>"
			+"<if test='dto.search_price1 != null and dto.search_price2 != null and dto.search_price2 lt dto.search_price1'>"
			+"and t.price between #{dto.search_price1} and #{dto.search_price2}"
			+"</if>"
			+"<if test='dto.search_payDate1 != null and dto.search_payDate2 != null'>"
			+"and t.paydate between #{dto.search_payDate1} and #{dto.search_payDate2}"
			+"</if>"
			+") temp"
			+"<if test='startIndex != null'>"
			+"where temp.no>=#{startIndex}"
			+"</if>"
			+"<if test='endIndex != null'>"
			+"and temp.no &lt;=#{endIndex}"
			+"</if>"
			+ "</script>"
			)
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({
			@Result(id=true,property="tallyid",column="tallyid"),
			@Result(property="type" , column="type"),
			@Result(property="price" , column="price"),
			@Result(property="payDate" , column="payDate")
		})

	public List<Tally> selectall(Map<String,Object> parme) ;

	//获取总条数
	 @Select("select count(*) from tally t"
			 +"<if test='dto.search_tallyid != null'>"
				+"and t.tallyid = #{dto.search_tallyid}"
				+"</if>"
				+"<if test='dto.search_type != null'>"
				+"and t.type like '%'||#{dto.search_type}||'%'"
				+"</if>"
				+"<if test='dto.search_price1 != null and dto.search_price2 != null and dto.search_price2 lt dto.search_price1'>"
				+"and t.price between #{dto.search_price1} and #{dto.search_price2}"
				+"</if>"
				+"<if test='dto.search_payDate1 != null and dto.search_payDate2 != null'>"
				+"and t.paydate between #{dto.search_payDate1} and #{dto.search_payDate2}"
				+"</if>")
	@Options(flushCache = false, timeout = 10000,useCache=true)
	public int getCount(TallyDTO dto);
	 
	//查询用户一条记录
		@Select("select * from t_role where role_id=#{id}")
		@Options(flushCache = false, timeout = 10000,useCache=true)
		@Results({ 
		    @Result(id = true, property="role_ID", column="role_id"),
			@Result(property="role_NAME",column="role_name"),
			@Result(property="role_DESC", column="role_desc" )
	})
		public abstract T_Role roleSelect(int id);
}
