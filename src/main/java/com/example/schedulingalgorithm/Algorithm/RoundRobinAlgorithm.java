package com.example.schedulingalgorithm.Algorithm;
import java.util.*;

import com.example.schedulingalgorithm.Process.PairOther;
import com.example.schedulingalgorithm.Process.ProcessObject;

public class RoundRobinAlgorithm {

    public static List<PairOther> pairList(List<ProcessObject> processList, int timeQuantum) {
        List<PairOther> pairOtherList = new ArrayList<>();
        Queue<ProcessObject> queue = new LinkedList<>();

        int n = processList.size();
        double time = 0;  // Thời gian hiện tại
        int completedProcesses = 0;  // Số tiến trình đã hoàn thành
        double[] remainingTime = new double[n];  // Lưu trữ thời gian còn lại của mỗi tiến trình
        boolean[] inQueue = new boolean[n];  // Kiểm tra xem tiến trình có đang trong hàng đợi hay không

        // Khởi tạo thời gian còn lại của các tiến trình
        for (int i = 0; i < n; i++) {
            remainingTime[i] = processList.get(i).getBurstTime();
        }

        // Sắp xếp danh sách tiến trình theo thời gian đến
        processList.sort(Comparator.comparingDouble(ProcessObject::getArrivalTime));

        int index = 0;

        // Thêm tiến trình đầu tiên vào hàng đợi nếu nó đã đến
        while (index < n && processList.get(index).getArrivalTime() <= time) {
            queue.add(processList.get(index));
            inQueue[index] = true;
            index++;
        }

        while (!queue.isEmpty()) {
            ProcessObject currentProcess = queue.poll();
            int processId = currentProcess.getId();
            int processIndex = processId - 1;  // ID từ 1 → n, còn mảng từ 0 → n-1

            // Xác định thời gian thực thi của tiến trình trong lần chạy này
            double executeTime = Math.min(timeQuantum, remainingTime[processIndex]);

            // Ghi nhận lịch trình thực thi
            pairOtherList.add(new PairOther(processId, time, executeTime));

            // Cập nhật thời gian
            time += executeTime;
            remainingTime[processIndex] -= executeTime;

            // Kiểm tra xem có tiến trình mới nào đến trong khoảng thời gian này không
            while (index < n && processList.get(index).getArrivalTime() <= time) {
                queue.add(processList.get(index));
                inQueue[index] = true;
                index++;
            }

            // Nếu tiến trình chưa hoàn thành, thêm lại vào queue
            if (remainingTime[processIndex] > 0) {
                queue.add(currentProcess);
            } else {
                completedProcesses++;
            }
        }

        return pairOtherList;
    }

    public static double[] waitingTime(List<ProcessObject> processList, int timeQuantum) {
        int n = processList.size();
        double[] wt = new double[n];  // Mảng chứa thời gian chờ
        double[] lastFinishTime = new double[n];  // Thời gian tiến trình cuối cùng kết thúc
        double[] completionTime = new double[n];  // Thời gian hoàn thành cuối cùng của tiến trình

        // Khởi tạo tất cả giá trị
        Arrays.fill(wt, 0);
        Arrays.fill(completionTime, 0);

        // Lấy danh sách lịch trình thực thi từ pairList
        List<PairOther> pairOtherList = pairList(processList, timeQuantum);

        // Tính thời gian hoàn thành của mỗi tiến trình
        for (PairOther pairOther : pairOtherList) {
            int j = pairOther.getId() - 1;
            lastFinishTime[j] = pairOther.getStartTime() + pairOther.getExecuteTime();
            completionTime[j] = lastFinishTime[j];
        }

        // Tính WT = TAT - BT
        for (int i = 0; i < n; i++) {
            wt[i] = completionTime[i] - processList.get(i).getArrivalTime() - processList.get(i).getBurstTime();
        }

        return wt;
    }

    public static double[] respondTime(List<ProcessObject> processList, int timeQuantum) {
        int n = processList.size();
        double[] rt = new double[n];
        boolean[] marked = new boolean[n];
        for (int i = 0; i < n; ++i) {
            rt[i] = 0.d;
            marked[i] = false;
        }
        List<PairOther> pairOtherList = pairList(processList, timeQuantum);
        for (PairOther pairOther : pairOtherList) {
            int j = pairOther.getId() - 1;
            if (!marked[j]) {
                marked[j] = true;
                rt[j] = pairOther.getStartTime() - processList.get(j).getArrivalTime();
            }
        }
        return rt;
    }

    public static double[] turnAroundTime(List<ProcessObject> processList, int timeQuantum) {
        double[] wt = waitingTime(processList, timeQuantum);
        double[] ta = new double[processList.size()];
        for (int i = 0; i < processList.size(); ++i) {
            ta[i] = wt[i] + processList.get(i).getBurstTime();
        }
        return ta;
    }
}
