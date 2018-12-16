package cn.nela.asynctaskdemo;

public class AsyncTask {
    private static AsyncTask mAsyncTask;

    public static AsyncTask getInstance() {
        if (mAsyncTask != null) {
            return mAsyncTask;
        } else {
            mAsyncTask = new AsyncTask();
            return mAsyncTask;
        }
    }

    AsyncTaskCallBack mAsyncTaskCallBack;

    public void addCallBack(AsyncTaskCallBack asyncTaskCallBack) {
        mAsyncTaskCallBack = asyncTaskCallBack;
    }

    public int realRunTask(int operationId, String taskMessage) {

        if (operationId % 2 == 1) {
            mAsyncTaskCallBack.asyncTaskResult(operationId, true);
        } else {
            mAsyncTaskCallBack.asyncTaskResult(operationId, false);
        }
        if (operationId == 10000) {
            //调用失败
            return -1;
        }
        return operationId;
    }
}
