create table tbl_users(
userId number,
userName varchar2(20),
userPassword varchar2(20),
userBirth Date,
userAddress varchar2(20),
userPhone varchar2(20)
)




insert into tbl_users values(1,'����','123',to_date('2001-01-01','yyyy-MM-dd'),'aaa','aaa')


private int userId;
	private String userName;
	private String userPassword;
	//���ڸ�ʽ��
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date userBirth;
	private String userAddress;
	private String userPhone;
	private int userStatus;
	
	
	select * from tbl_users
	select * from tally