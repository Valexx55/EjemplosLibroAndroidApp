package edu.val.ejemploslibroandroidapp.tema10;

import java.util.List;

public class ResultadoCanciones {

    private int resultCount;
    private List<Cancion> results;

    public ResultadoCanciones() {
    }

    public ResultadoCanciones(int resultCount, List<Cancion> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Cancion> getResults() {
        return results;
    }

    public void setResults(List<Cancion> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResultadoCanciones{" +
                "resultCount=" + resultCount +
                ", results=" + results +
                '}';
    }
}
