package cn.com.ckg.shootGame;
/**
 * ���С�۷�Ľ����ӿ�
 * 
 * �����ͳ��󷽷�
 * @author 1
 *
 */
public interface Award {
	//ʹ�ü���ķ�ʽ:����һ������,ʹ�����Ĺ̶�ֵ�������Ƿ�Ϊ˫������
	int DOUBLE_FIRE = 0;//��0����ʾ˫������
	int SUPER_FIRE = 2;
	int LIFE = 1;//��ʾС�۷䱻���֮��,��������ֵ(1����������ֵ)
	//�����жϵ����ǽ�������ֵ����˫�������ķ���
	public int getType();//��ȡ�������͵ķ���
	

}
