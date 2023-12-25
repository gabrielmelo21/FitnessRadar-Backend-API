package com.hybridTheory.FitnessRadarBackendAPI.Controllers;

import com.hybridTheory.FitnessRadarBackendAPI.Utils.DataAtual;
import com.hybridTheory.FitnessRadarBackendAPI.DTO.AlimentosDTO;
import com.hybridTheory.FitnessRadarBackendAPI.DTO.AlimentosIngeridosDTO;
import com.hybridTheory.FitnessRadarBackendAPI.DTO.CaloriasDTO;
import com.hybridTheory.FitnessRadarBackendAPI.DTO.PesoDTO;
import com.hybridTheory.FitnessRadarBackendAPI.Models.*;
import com.hybridTheory.FitnessRadarBackendAPI.Repositories.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class API_CONTROLLER {
    @Autowired
    public final AlimentosRepository alimentosRepository;
    public final AlimentosIngeridosRepository alimentosIngeridosRepository;
    public final CaloriasRepository caloriasRepository;
    public final HistoricoDeficitCaloricoRepository historicoDeficitCaloricoRepository;
    public final PesoRepository pesoRepository;



    @Getter
    @Setter
    private List<Long> itemIds;





    // Controller de Alimentos
    @GetMapping("/alimentos")
    public List<Alimentos> listAlimentos() {
        return alimentosRepository.findAll();
    }

    @PostMapping("/addAlimentos")
    public ResponseEntity<Alimentos> saveAlimentos(@RequestBody @Valid AlimentosDTO alimentosDTO) {
        var alimentosInstancia = new Alimentos();
        BeanUtils.copyProperties(alimentosDTO, alimentosInstancia);
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosRepository.save(alimentosInstancia));
    }

    @PostMapping("/addAlimentosBatch")
    public ResponseEntity<List<Alimentos>> saveAlimentosBatch(@RequestBody @Valid List<AlimentosDTO> alimentosDTOList) {
        List<Alimentos> alimentosInstanciaList = new ArrayList<>();
        for (AlimentosDTO alimentosDTO : alimentosDTOList) {
            var alimentosInstancia = new Alimentos();
            BeanUtils.copyProperties(alimentosDTO, alimentosInstancia);
            alimentosInstanciaList.add(alimentosInstancia);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosRepository.saveAll(alimentosInstanciaList));
    }

    @DeleteMapping("/ListaAlimentosDelete")
    public ResponseEntity<String> deleteListaAlimentos(@RequestBody List<AlimentosDTO> listaAlimentosDTO) {
        List<Long> listaIds = new ArrayList<>();

        for (AlimentosDTO a : listaAlimentosDTO) {
            listaIds.add(a.getId());
        }
        this.alimentosRepository.deleteAllById(listaIds);
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @DeleteMapping("/deletarAlimento/{id}")
    public ResponseEntity<Object> deleteAlimentoById(@PathVariable(value = "id") Long id){
        Optional<Alimentos> alimentoSelected = alimentosRepository.findById(id);
        alimentosRepository.delete(alimentoSelected.get());
        return ResponseEntity.status(HttpStatus.OK).body("Alimento Deletado com sucesso!");
    }



    // Controller de Alimentos Ingeridos
    @GetMapping("/listarAlimentosIngeridos")
    public List<AlimentosIngeridos> list(){
        return alimentosIngeridosRepository.findAll();
    }
    @PostMapping("/addAlimentosIngeridos")
    public ResponseEntity<AlimentosIngeridos> save(@RequestBody @Valid AlimentosIngeridosDTO dto){
        var alimentos = new AlimentosIngeridos();
        BeanUtils.copyProperties(dto, alimentos);
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentosIngeridosRepository.save(alimentos));
    }
    @DeleteMapping("/deletarAlimentosIngeridos/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){
        Optional<AlimentosIngeridos> alimentoSelected = alimentosIngeridosRepository.findById(id);
        alimentosIngeridosRepository.delete(alimentoSelected.get());
        return ResponseEntity.status(HttpStatus.OK).body("Alimento Deletado com sucesso!");
    }










//CALORIAS CONTROLLER - grava as calorias diarias que o usuario ingeriu e a taxa basal dele, isso sera um historico onde é atualizado
    // todos dias que sera a base para os dados de deficit calorico

    @GetMapping("/calorias")
    public List<Calorias> listaCalorias(){return caloriasRepository.findAll();}
    @PostMapping("/addCalorias")
    public ResponseEntity<Calorias> saveCalorias(@RequestBody @Valid CaloriasDTO caloriasDTO) {
        Calorias caloriasInstancia = new Calorias();
        BeanUtils.copyProperties(caloriasDTO, caloriasInstancia);
        return ResponseEntity.status(HttpStatus.CREATED).body(caloriasRepository.save(caloriasInstancia));
    }

    @PutMapping("/updateCalorias/{id}")
    public ResponseEntity<Calorias> updateCalorias(
            @PathVariable Long id,
            @RequestBody @Valid CaloriasDTO caloriasDTO
    ) {
        Optional<Calorias> caloriasOptional = caloriasRepository.findById(id);

        if (caloriasOptional.isPresent()) {
            Calorias calorias = caloriasOptional.get();

            calorias.setCalorias_atual(caloriasDTO.calorias_atual());

            calorias.setTmb(2500L);

            Long deficit_calorico = caloriasDTO.calorias_atual() - caloriasDTO.tmb();

            calorias.setDeficit_calorico(deficit_calorico);

            String dataAtual = new DataAtual().dataDiaAtual();

            calorias.setData_dia(dataAtual);

            Calorias updatedCalorias = caloriasRepository.save(calorias);

            return ResponseEntity.ok(updatedCalorias);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



// HISTORICO DE DEFICIT CALORICO

    @GetMapping("/historico")
    public List<HistoricoDeficitCalorico> listHistorico(){
        return historicoDeficitCaloricoRepository.findAll();
    }

    @PostMapping("/addHistorico")
    public ResponseEntity<String> addHistorico(){

        //Salvar os dados antigos no historico
        HistoricoDeficitCalorico n = new HistoricoDeficitCalorico();
        n.setData_dia("30/09");
        n.setDeficit_calorico((long) 180);
        historicoDeficitCaloricoRepository.save(n);



        return ResponseEntity.status(HttpStatus.OK).body("Dados inseridos com sucesso");
    }

    @DeleteMapping("/deleteHistorico")
    public ResponseEntity<String> deleteHistorico(){
        historicoDeficitCaloricoRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Historico deletado");
    }

    // PESO
    @GetMapping("/peso")
    public List<Peso> listPeso(){return pesoRepository.findAll();}
    @PostMapping("/addPeso")
    public ResponseEntity<Peso> addPeso(@RequestBody @Valid PesoDTO pesoDTO){
        Peso peso = new Peso();
        BeanUtils.copyProperties(pesoDTO, peso);
        return ResponseEntity.status(HttpStatus.CREATED).body(pesoRepository.save(peso));
    }
    @DeleteMapping("/deleteAllPeso")
    public String deletePeso(){
        pesoRepository.deleteAll();
        return "Deletado com sucesso";
    }
    @PutMapping("/updatePeso/{id}")
    public ResponseEntity<Peso> updatePeso(@PathVariable Long id, @RequestBody @Valid PesoDTO pesoDTO) throws Exception {
        Optional<Peso> pesoOptional = pesoRepository.findById(id);
        if (pesoOptional.isPresent()){
            Peso peso = pesoOptional.get();
            BeanUtils.copyProperties(pesoDTO, peso);
            return new ResponseEntity<>(peso, HttpStatus.OK);
        }else{
            throw new Exception("Falha ao atualizar");
        }


    }
    // preciso de um update geral - de peso e de peso objetivo
    // toda vez que atualizamos, devemos registrar a data para termos os registros (Peso - Data)

    // deleta o atual e adiciona um novo com o peso objetivo, esse peso objetivo pode ser alterado depois

}

