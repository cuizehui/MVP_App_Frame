package cn.nela.asynctaskdemo;

public class Test {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.runAsyncTask("1");
        manager.runAsyncTask("2");
        manager.runAsyncTask("3");
    }
}
