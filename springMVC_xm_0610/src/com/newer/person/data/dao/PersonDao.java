package com.newer.person.data.dao;

import java.util.List;

import com.newer.common.entry.*;
import com.newer.dto.PlanDTO;

public interface PersonDao {

	//��ѯ�û�һ����¼
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#roleSelect(int)
	 */
	public abstract T_Role roleSelect(int id);

	//��ѯԱ��һ����¼
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personSelect(int)
	 */
	public abstract T_Employee personSelect(int id);

	//��ѯ�����һ����¼
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#taskSelect(int)
	 */
	public abstract T_Task taskSelectById(int id);
	
	//��ѯ�ƻ���һ����¼
	public abstract T_Plan planSelectById(int id);

	//�����ѯ����Ա�����
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#taskSelect()
	 */
	public abstract List<T_Task> taskSelect(int implementorId,int pageNo, int pageSize);

	//�ƻ���ѯ����������
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#planSelect()
	 */
	public abstract List<T_Plan> planSelect(int taskId);

	//����Ա��
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personInsert(com.newer.common.entry.T_Employee)
	 */
	public abstract int PlanInsert(T_Plan plan);

	//ɾ���ƻ�
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#personDelete(int)
	 */
	public abstract int PlanDelete(int id);

	//�޸ļƻ�
	/* (non-Javadoc)
	 * @see com.newer.person.data.dao.PersonDao#planUpdate(com.newer.common.entry.T_Plan)
	 */
	public abstract int planUpdate(T_Plan plan);

	//��ѯ�ƻ�����
	public abstract int jihuaCount(int rnwuId);
	
	//�޸�����״̬
	public abstract int StatusUpdate(String statu,int renwuId);
	
	//�ƻ���ϲ�ѯ
	public abstract List<Object> query(PlanDTO dto,int pageNo,int pageSize,int id);
	
	//��ȡ������
	public abstract int Count(PlanDTO dto,int id);
	
	//��¼��ѯ
	public abstract List<T_Employee> selectEmployees(String sql, Object[] params);
	
	//��ȡ������
	public abstract int SelectZongShu(String sql, List<Object> params);
}