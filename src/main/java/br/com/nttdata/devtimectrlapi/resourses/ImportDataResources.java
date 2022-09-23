package br.com.nttdata.devtimectrlapi.resourses;

import br.com.nttdata.devtimectrlapi.domain.Cargo;
import br.com.nttdata.devtimectrlapi.domain.Empresa;
import br.com.nttdata.devtimectrlapi.repositories.CargoRepository;
import br.com.nttdata.devtimectrlapi.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/data")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImportDataResources {

    private final CargoRepository cargoRepository;
    private final EmpresaRepository empresaRepository;

    @PostMapping
    public ResponseEntity<List<Cargo>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Cargo> productList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Cargo cargo = new Cargo();

                XSSFRow row = worksheet.getRow(index);
                Integer id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String empresa = row.getCell(2).getStringCellValue();

                List<Empresa> first = empresaRepository.findByNomeContainingIgnoreCase(empresa);
                Optional<Empresa> first1 = first.stream().findFirst();
                cargo.setNome(name);
                cargo.setEmpresa(first1.isPresent() ? first1.get() : null);

                cargoRepository.save(cargo);
            }
        }

        return new ResponseEntity<>(productList, status);
    }
}
