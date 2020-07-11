package com.newer.mysm.data.util;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class Provider {



	public String selectByPae(final TallyDTO dto) {
		
		
		StringBuffer sql=new StringBuffer("select * from (select row_number() over(order by tallyid) no,t.* from tally tly where 1=1");
			
			if (dto.getSearch_tallyid() != null && dto.getSearch_tallyid()  > 0){
				sql.append(" and tly.tallyid = #{dto.search_tallyid}");
			}
		if(dto.getSearch_type() != null && dto.getSearch_type()!=""){
			sql.append("and tly.type like '%'||#{dto.search_type}||'%'");
		}
		if(dto.getSearch_price1() != null && dto.getSearch_price2() != null && dto.getSearch_price1()>=0.0 &&  dto.getSearch_price2()>0.0){
			sql.append("and tly.price between #{dto.search_price1} and #{dto.search_price2}");
		}
          if(dto.getSearch_payDate1() != null && dto.getSearch_payDate2() != null){
        	  sql.append("and tly.paydate between #{dto.search_payDate1} and #{dto.search_payDate2}");
          }
          
        	  sql.append("1=1) temp where 1=1");
			
          
//          if (parme.get("endIndex") != null && parme.get("endIndex")!=""){
//        	  sql.append("temp.no <=#{endIndex}");
//          }
//          if (parme.get("startIndex") != null && parme.get("startIndex")!=""){
//        	  sql.append("temp.no>=#{startIndex}");
//          }
        	  sql.append("temp.no <=2");
        	  sql.append("temp.no>=1");
		String str=new String(sql);
		return str;
	}
}
