package worker;

import model.Job;
import queue.JobQueue;
import util.DocxUtil;
import util.PdfUtil;
import dao.JobDAO;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JobWorker implements Runnable {
    public void run() {
        while (true) {
            try {
                Job job = JobQueue.take();
                System.out.println("🛠️ Xử lý file: " + job.getFilename());

                String text = PdfUtil.extractText(Paths.get(job.getOutputPath()));
                Path outputDocx = Paths.get("output", job.getFilename().replace(".pdf", ".docx"));
                DocxUtil.createDocx(text, outputDocx);

                Job doneJob = new Job(job.getFilename(), outputDocx.toString());
                JobDAO.save(doneJob);
                System.out.println("✅ Đã xử lý xong: " + doneJob.getOutputPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
