package cn.nela.asynctaskdemo;

import java.util.LinkedList;
import java.util.Queue;

public class TaskManager implements AsyncTaskCallBack {


    private int mOperationId = 0;
    private boolean mIsStartTask = false;
    private Queue<AsyncTaskInfo> asyncTaskInfoQueue = new LinkedList<>();

    public TaskManager() {
        super();
        AsyncTask.getInstance().addCallBack(this);
    }

    private class AsyncTaskInfo {
        public int operationId;
        public String taskMessage;
    }

    public int runAsyncTask(String taskMessage) {
        AsyncTaskInfo asyncTaskInfo = new AsyncTaskInfo();
        asyncTaskInfo.taskMessage = taskMessage;
        asyncTaskInfo.operationId = ++mOperationId;
        asyncTaskInfoQueue.add(asyncTaskInfo);
        if (!mIsStartTask) {
            startTask();
        }
        return mOperationId;
    }

    private void startTask() {
        if (asyncTaskInfoQueue.size() > 0 && !mIsStartTask) {
            mIsStartTask = true;
            AsyncTaskInfo asyncTaskInfo = asyncTaskInfoQueue.poll();
            int operationId = AsyncTask.getInstance().realRunTask(asyncTaskInfo.operationId, asyncTaskInfo.taskMessage);
            if (operationId == -1) {
                mIsStartTask = false;
                startTask();
            }
        }
    }

    @Override
    public void asyncTaskResult(int operationId, boolean result) {
        mIsStartTask = false;
        System.out.println("operationId = [" + operationId + "], result = [" + result + "]");
        //如果需要上层处理 则做本层回调抛出给Manager层
        //如果需要继续处理 则将回调封在本层。
        startTask();
    }
}
