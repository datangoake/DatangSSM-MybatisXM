package com.newer.mysm.service;

import java.util.List;
import java.util.Map;

import com.newer.mysm.data.entity.T_Employee;
import com.newer.mysm.data.entity.T_Plan;
import com.newer.mysm.data.entity.T_Role;
import com.newer.mysm.data.entity.T_Task;
import com.newer.dto.PlanDTO;
import com.newer.mysm.data.util.TallyDTO;
import com.newer.mysm.data.util.gaojifenye;

public interface Itallyservice {

//	public List selectbypage(Map<String,Object> map);
//	public int getTatleCount(TallyDTO dto);
	//��ѯ�û�һ����¼
		public abstract T_Role roleSelect(int id);
		
		//��ѯԱ��һ����¼
		public abstract T_Employee personSelect(int id);

		//��ѯ�����һ����¼
		public abstract T_Task taskSelectById(int id);

		//����ȫ����ѯ
		public abstract List<T_Task> taskSelect(int implementorId,int pageNo, int pageSize);

		//�ƻ�ȫ����ѯ
		public abstract List<T_Plan> planSelect(int taskId);

		//�½��ƻ�
		public abstract int PlanInsert(T_Plan plan);

		//ɾ���ƻ�
		public abstract int PlanDelete(int id);

		//�޸ļƻ�
		public abstract int planUpdate(T_Plan plan);
		
		//���������Ų�ѯ�ƻ�����
		public abstract int jihuaCount(int rnwuId);
		
		//�޸�����״̬
		public abstract int StatusUpdate(String statu,int renwuId);
		
		//��ѯ�ƻ���һ����¼
		public abstract T_Plan planSelectById(int id);
		
		//�ƻ���ϲ�ѯ
		public abstract List<gaojifenye> query(Map<String, Object>map);
			
		//��ȡ������
		public abstract int Count(Map<String, Object> map);
		
		//��¼��ѯ
		public abstract T_Employee checkLogin(String employeeName, String password);
		//��ȡ������
			public abstract int SelectZongShu(int id);
}
