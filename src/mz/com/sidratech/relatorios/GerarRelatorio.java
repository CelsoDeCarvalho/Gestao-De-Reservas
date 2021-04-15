package mz.com.sidratech.relatorios;

import com.github.gbfragoso.JasperViewerFX;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import mz.com.sidratech.controller.PratoController;
import mz.com.sidratech.repository.Repository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author celso
 */
public class GerarRelatorio {

    public void getReport(String nome) throws JRException, FileNotFoundException {
        Repository repository = new Repository();
        repository.getRelatorios();

        HashMap param = new HashMap();
        param.put("usuario", nome);

        URL uri = getClass().getResource("Relatorio.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(uri);

        JRDataSource dataSource = new JRBeanCollectionDataSource(Repository.relatorios);

        //JasperReport report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/mz/com/sidratech/relatorios/Relatorio.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);

        new JasperViewerFX().viewReport("Relatorio", jasperPrint);

    }

    public void getRecibo(String usuario, String nomeEnt, String local, String tipo, String email, String cliente, String nome, Long phone, Integer room, Double valor, Double total, Date in, Date out) throws JRException, FileNotFoundException {
        Repository repository = new Repository();
        repository.getRelatorios();

        HashMap param = new HashMap();
        param.put("usuario", "Celso");
        param.put("nomeEntidade", nomeEnt);
        param.put("localizacao", local);
        param.put("email", email);
        param.put("tipo", tipo);
        param.put("cliente", cliente);
        param.put("nome", nome);
        param.put("phone", phone);
        param.put("total", total);
        param.put("quarto", room);
        param.put("in", in);
        param.put("out", out);
        param.put("valor", valor);

        URL uri = getClass().getResource("Recibo.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(uri);

        JRDataSource dataSource = new JRBeanCollectionDataSource(Repository.relatorios);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);

        new JasperViewerFX().viewReport("Recibo", jasperPrint);

    }

    public void getFatura(String nome, Double total, String local) throws JRException, FileNotFoundException {
        Repository repository = new Repository();
        repository.getRelatorios();

        HashMap param = new HashMap();
        param.put("nome", nome);
        param.put("local", local);
        param.put("total", total);

        URL uri = getClass().getResource("Fatura.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(uri);

        JRDataSource dataSource = new JRBeanCollectionDataSource(PratoController.pratos);

        //JasperReport report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/mz/com/sidratech/relatorios/Relatorio.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);

        new JasperViewerFX().viewReport("Fatura", jasperPrint);

    }

}
