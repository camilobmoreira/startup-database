package br.gov.sp.fatec.startupdatabase.service;

import br.gov.sp.fatec.startupdatabase.model.Fundador;
import br.gov.sp.fatec.startupdatabase.model.Startup;

public class StartupService {

    public Startup criaStartup(String nomeFundador, String cpf, String nomeStartup, String cnpj) {
        if (cpf != null && !cpf.isEmpty()) {
            Long.parseLong(cpf);
        }

        Fundador fundador = new Fundador();
        fundador.setNome(nomeFundador);
        fundador.setCpf(cpf);

        Startup startup = new Startup();
        startup.setNome(nomeStartup);
        startup.setCnpj(cnpj);
        startup.setFundador(fundador);

        return startup;
    }

    public Startup buscaPorNomeFundador(String nomeFundador) {
        return null;
    }
}
