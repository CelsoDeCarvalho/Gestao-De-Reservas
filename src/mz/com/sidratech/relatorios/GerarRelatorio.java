package mz.com.sidratech.relatorios;

import com.github.gbfragoso.JasperViewerFX;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import mz.com.sidratech.repository.Repository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
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

    public void getReport() throws JRException, FileNotFoundException {
        Repository repository = new Repository();
        repository.getRelatorios();

        URL uri = getClass().getResource("Relatorio.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(uri);

        JRDataSource dataSource = new JRBeanCollectionDataSource(Repository.relatorios);

        //JasperReport report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/mz/com/sidratech/relatorios/Relatorio.jrxml");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        new JasperViewerFX().viewReport("Relatorio", jasperPrint);

    }

}
