package cn.kgc.shootGame;
/**
 * @author Administrator
 * 针对小蜜蜂的奖励接口
 * 接口中只能存放常量和抽象方法
 */
public interface Award {
    //使用假设的方式:假设一个常量,使用它的固定值来表示奖励是否为双倍火力
	int DOUBLE_FIRE=0;//用0来表示奖励双倍火力
	int LIFE=1;//表示小蜜蜂被打掉之后，奖励的生命值类型(1来表示奖励生命值)
	//用来判断到底是奖励生命值还是双倍火力的方法
	public int getType();//获取奖励类型的方法
	
}
