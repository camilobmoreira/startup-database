package br.gov.sp.fatec.startupdatabase.service;

import br.gov.sp.fatec.startupdatabase.model.Fundador;
import br.gov.sp.fatec.startupdatabase.model.Startup;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.stream.Stream;

//@RunWith(JUnitPlatform.class)
//@ExcludeTags("fail") //fixme não tá funcionando
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
        Assertions.assertThrows(RuntimeException.class, () -> {
            this.startupService.criaStartup("Aisdba", "12345678912", "qrqsadq", "23523");
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
    @Tag("fail")
    public void fail() {
        Startup startup1 = this.startupService.criaStartup("asd", "23423", "qrqsadq", "23523");
        List<Startup> startups = this.startupService.buscaPorNomeFundador("asd");
        Assertions.fail();
        Assertions.assertNotNull(startups);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456789", "987654321", "465132789"})
    public void parametezidedTestValueSource(String cpf) {
        Startup startup1 = this.startupService.criaStartup("Jose", cpf, "nome aleatorio SA", "897541384");
        Assertions.assertEquals(startup1, this.startupService.buscarPorCpfFundador(cpf));
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 2000, 3000",
            "2500, 3000, 5500"
    })
    public void parametizedTestCsvSource(Double a, Double b, Double c) {
        this.startup.addFundos(a);
        this.startup.addFundos(b);
        Assertions.assertEquals(c, this.startup.getCapital());
    }

    @ParameterizedTest
    @MethodSource("fundosProvider")
    public void parametizedTestMethodSource(Double a, Double b, Double c) {
        this.startup.addFundos(a);
        this.startup.addFundos(b);
        Assertions.assertEquals(c, this.startup.getCapital());
    }

    private static Stream<Arguments> fundosProvider() {
        return Stream.of(
                Arguments.of(1000.0, 2000.0, 3000.0),
                Arguments.of(2500.0, 3000.0, 5500.0)
        );
    }

    @RepeatedTest(5)
    public void repeatedTest() {
        Assertions.assertEquals(1, this.startupService.buscaPorNomeFundador("Kellycrusha").size());
    }
}
