package com.peierlong.jvm;

/**
 * 包名: com.peierlong.jvm
 * 创建人 : Elong
 * 时间: 2017/1/22 下午4:50
 * 描述 : 1. 对象可以被GC时自我拯救  2. 这种自救的机会只有一次，因为一个对象的finalize()方法
 *        注：一般情况不建议使用
 */
public class FinalizeEscapeGC {
    private static FinalizeEscapeGC SAVE_LOCK = null;

    public void isAlive() {
        System.out.println("yes, I am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize is executed!");
        FinalizeEscapeGC.SAVE_LOCK = this;
    }

    public static void main(String[] args) throws Exception{
        SAVE_LOCK = new FinalizeEscapeGC();

        // 对象第一次成功拯救自己
        SAVE_LOCK = null;
        System.gc();
        // 因为finalize方法的优先级很低, 所以暂停0.5秒等待它
        Thread.sleep(500);
        if (SAVE_LOCK != null) {
            SAVE_LOCK.isAlive();
        } else {
            System.out.println("no, I am dead :(");
        }

        //代码完全相同，自救失败
        SAVE_LOCK = null;
        System.gc();
        // 因为finalize方法的优先级很低, 所以暂停0.5秒等待它
        Thread.sleep(500);
        if (SAVE_LOCK != null) {
            SAVE_LOCK.isAlive();
        } else {
            System.out.println("no, I am dead :(");
        }

    }

}
