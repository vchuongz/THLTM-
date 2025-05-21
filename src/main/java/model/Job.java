package model;

public class Job {
    private String filename;
    private String outputPath;

    public Job(String filename, String outputPath) {
        this.filename = filename;
        this.outputPath = outputPath;
    }

    public String getFilename() {
        return filename;
    }

    public String getOutputPath() {
        return outputPath;
    }
}
