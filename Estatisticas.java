public class Estatisticas {
    private String totalGames;
    private String vitoriasPercentual;
    private String derrotasPercentual;
    private String empatesPercentual;

    public Estatisticas(String totalGames, String vitoriasPercentual2, String derrotasPercentual2, String empatesPercentual2) {
        this.totalGames = totalGames;
        this.vitoriasPercentual = vitoriasPercentual2;
        this.derrotasPercentual = derrotasPercentual2;
        this.empatesPercentual = empatesPercentual2;
    }

    public String getTotalGames() {
        return totalGames;
    }

    public String getVitoriasPercentual() {
        return vitoriasPercentual;
    }

    public String getDerrotasPercentual() {
        return derrotasPercentual;
    }

    public String getEmpatesPercentual() {
        return empatesPercentual;
    }

    public String toCSV(){
        return "" + totalGames + "," + vitoriasPercentual + "," + derrotasPercentual + "," + empatesPercentual + "";
    }
}
