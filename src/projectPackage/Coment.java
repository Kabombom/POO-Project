package projectPackage;

public class Coment {
    private String comment;
    private double score;

    public Coment(String comment, double score) {
        this.comment = comment;
        this.score = score;
    }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    @Override
    public String toString() {
        return "Coment{" +
                "comment='" + comment + '\'' +
                ", score=" + score +
                '}';
    }
}
