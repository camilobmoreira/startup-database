package br.gov.sp.fatec.startupdatabase.service;

import br.gov.sp.fatec.startupdatabase.model.Fundador;
import br.gov.sp.fatec.startupdatabase.model.Startup;
import org.junit.jupiter.api.*;


public class StartupServiceTest {

    StartupService startupService = new StartupService();
    Startup startup;

    @BeforeEach
    public void preparaCenario() {
        this.startup = this.startupService.criaStartup("Kellycrusha", "12345678912", "Parker Unlimited", "987654321564");
    }

    @Test
    @DisplayName("Nome fundador test")
    public void assertEqualsEDisplayName() {
        Assertions.assertEquals("Kellycrusha", this.startup.getFundador().getNome(),  "Nome fundador");
    }

    @Test
    public void assertSame() {
        Startup startup1 = new Startup();
        this.startup = startup1;
        Assertions.assertSame(startup1, this.startup);
    }

    @Test
    public void assertThrows() {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            this.startupService.criaStartup("Aisdba", "asd", "qrqsadq", "23523");
        });
    }

    @Test
    public void assertAll() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("12345678912", this.startup.getFundador().getCpf()),
                () -> Assertions.assertEquals("123", this.startup.getCnpj()),
                () -> Assertions.assertEquals("Kellycrusha", this.startup.getFundador().getNome()),
                () -> Assertions.assertEquals(new Fundador(), this.startup.getFundador())
        );
    }

    @Test
    public void assumeTruePassando() {
        Startup startup1 = this.startupService.criaStartup("Aisdba", "23423", "qrqsadq", "23523");
        Assumptions.assumeTrue(startup1.getFundador() != null);
        Assertions.assertNotEquals("", startup1.getFundador().getNome());
    }

    @Test
    public void assumeTrueFalhando() {
        Startup startup1 = this.startupService.criaStartup("Aisdba", "23423", "qrqsadq", "23523");
        Assumptions.assumeTrue(startup1.getFundador() == null);
        Assertions.assertThrows(NullPointerException.class, () -> startup1.getFundador().getNome());
    }

    @Test
    @Tag("Arrumar - fail")
    public void fail() {
        Startup startup1 = this.startupService.criaStartup("asd", "23423", "qrqsadq", "23523");
        Startup startup2 = this.startupService.buscaPorNomeFundador("asd");
        Assertions.fail();
        Assertions.assertEquals(startup1, startup2);
    }
}
