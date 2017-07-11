package com.du.cat;

import java.util.ArrayList;
import java.util.List;

public class CrossRiver {
	static List<Animail> leftlist = new ArrayList<Animail>();// 左岸有的动物
	static List<Animail> rigthlist = new ArrayList<Animail>();// 右岸有的动物
	static boolean flag = true;// 人的位置在左岸
	// 记录移动前的情况
	static List<List<Animail>> movelist = new ArrayList<List<Animail>>();// 移动操作
	public static void main(String[] args) {
		// 初始化各种动物间的制约关系
		initAnimair();
		// 直到右岸的动物为3为止
		int i = 0;
		while (rigthlist.size() != 3) {
			i = i + 1;
			System.out.println("第" + i + "次移动开始");
			if (flag) {
				// 向右移动
				move(flag);
			} else {
				// 向左移动
				move(flag);
			}
		}

	}
/***
 * 初始化左岸动物
 */
	private static void initAnimair() {
		Animail cat = new Animail();
		// cat 0 dog 1 finsh 2 -1:没有
		cat.setAfraid(1);
		cat.setController(2);
		Animail dog = new Animail();
		dog.setAfraid(-1);
		dog.setController(1);
		Animail finsh = new Animail();
		finsh.setAfraid(2);
		finsh.setController(3);
		leftlist.add(cat);
		leftlist.add(dog);
		leftlist.add(finsh);
	}
/***
 * 移动操作
 * @param flagmove
 */
	public static void move(boolean flagmove) {
		// 移动操作
		// 是否有过这样的移动
		boolean move = false;
		Animail animair = new Animail();
		if (flagmove) {
			animair = leftlist.remove(0);
			rigthlist.add(animair);
			if (movelist.size() > 0) {
				for (List<Animail> list : movelist) {
					if (list.contains(leftlist)) {
						move = true;
					}
				}
			}

		} else {
			// 要不就一直无线循环了
			// 当假设人不在时右岸有制约时，才开始移动
			boolean cr = false;
			if (rigthlist.size() >= 2) {
				if (rigthlist.size() == 2) {
					cr = rigthlist.get(0).check(rigthlist.get(1));
				} else {
					cr = rigthlist.get(0).check(rigthlist.get(1));
					if (!cr) {
						cr = rigthlist.get(0).check(rigthlist.get(2));
					} else if (!cr) {
						cr = rigthlist.get(1).check(rigthlist.get(2));
					}
				}
				if (cr) {
					animair = rigthlist.remove(0);
					leftlist.add(animair);
					for (List<Animail> list : movelist) {
						if (list.contains(leftlist)) {
							move = true;
						}
					}

				}
			}
		}

		if (!move) {
			// 记录每次移动之后的左边操作
			movelist.add(leftlist);
			// 检查制约条件 true 不能共存
			// 人的位置的更新
			flag = !flag;
			// cat 1 dog -1 finsh 2 打印移动的结果
			whatAnimair(flag, animair);
			// 人在右岸 检测左岸
			boolean check = false;// 检测通过，不用检测的情况
			// 人在左岸 检测右岸
			if (flag) {
				// 剩余岸边不少于2个时进行检测
				if (rigthlist.size() >= 2) {
					if (rigthlist.size() == 2) {
						check = rigthlist.get(0).check(rigthlist.get(1));
					} else {
						check = rigthlist.get(0).check(rigthlist.get(1));
						if (!check) {
							check = rigthlist.get(0).check(rigthlist.get(2));
						} else if (!check) {
							check = rigthlist.get(1).check(rigthlist.get(2));
						}
					}
				}
			} else {
				// 剩余岸边不少于2个时进行检测
				if (leftlist.size() >= 2) {
					if (leftlist.size() == 2) {
						check = leftlist.get(0).check(leftlist.get(1));
					} else {
						check = leftlist.get(0).check(leftlist.get(1));
						if (!check) {
							check = leftlist.get(0).check(leftlist.get(2));
						} else if (!check) {
							check = leftlist.get(1).check(leftlist.get(2));
						}
					}
				}
			}

			if (check) {
				// 有制约的矛盾 回退
				if (flagmove) {
					if (animair != null) {
						// 有可能是渔夫自己单独过河的
						rigthlist.remove(animair);
						leftlist.add(animair);
					}
				} else {
					leftlist.remove(animair);
					rigthlist.add(animair);
				}
				flag = !flag;
				System.out.println("有制约条件，回退到上一步");
			} else {
				System.out.println("成功的移动");
			}
		} else {
			System.out.println("有过这样的移动" + leftlist);
		}

	}

	/***
	 * 移动的结果打印
	 * 
	 * @param flagmove
	 * @param animair
	 */
	private static void whatAnimair(boolean flagmove, Animail animair) {
		String movejg = "";
		if (flagmove) {
			movejg += "左岸";
		} else {
			movejg += "右岸";
		}
		if (animair.getAfraid() == 1) {
			System.out.println("猫被移动到" + movejg);
		} else if (animair.getAfraid() == -1) {
			System.out.println("狗被移动到" + movejg);
		} else if (animair.getAfraid() == 2) {
			System.out.println("鱼被移动到" + movejg);
		} else {
			System.out.println("渔夫自己移动到" + movejg);
		}

	}
}