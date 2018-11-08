package br.gov.sp.fatec.startupdatabase.service;

import br.gov.sp.fatec.startupdatabase.model.Fundador;
import br.gov.sp.fatec.startupdatabase.model.Startup;

import java.util.ArrayList;
import java.util.List;

public class StartupService {

    List<Startup> startups = new ArrayList<>();

    public Startup criaStartup(String nomeFundador, String cpf, String nomeStartup, String cnpj) {
        this.validaCpf(cpf);
        Fundador fundador = new Fundador();
        fundador.setNome(nomeFundador);
        fundador.setCpf(cpf);

        Startup startup = new Startup();
        startup.setNome(nomeStartup);
        startup.setCnpj(cnpj);
        startup.setFundador(fundador);

        this.startups.add(startup);

        return startup;
    }

    private void validaCpf(String cpf) {
        startups.forEach(startup -> {
            if (startup.getFundador().getCpf().equalsIgnoreCase(cpf)) {
                throw new RuntimeException("Cpf já cadastrado");
            }
        });
    }

    public List<Startup> buscaPorNomeFundador(String nomeFundador) {
        List<Startup> startupList = new ArrayList<>();
        startups.forEach(startup -> {
            if (startup.getFundador().getNome().equalsIgnoreCase(nomeFundador)) {
                startupList.add(startup);
            }
        });
        return startupList;
    }


    public Startup buscarPorCpfFundador(String cpf) {
        for (Startup startup : this.startups) {
            if (startup.getFundador().getCpf().equalsIgnoreCase(cpf)) {
                return startup;
            }
        }
        return null;
    }
}
