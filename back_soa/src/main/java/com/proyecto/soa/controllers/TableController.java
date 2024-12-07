package com.proyecto.soa.controllers;

import com.proyecto.soa.model.dtos.TableKanbanResponse;
import com.proyecto.soa.model.dtos.TableRequest;
import com.proyecto.soa.services.TableKanbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table")
@RequiredArgsConstructor
public class TableController {
    private final TableKanbanService tableKanbanService;

    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> getFindByUser(@PathVariable Long idUser){
        try {
            TableKanbanResponse tableKanbanResponse = tableKanbanService.getFindByUser(idUser);
            return ResponseEntity.ok(tableKanbanResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar la tabla");
        }
    }

    @GetMapping("/group/{idGroup}")
    public ResponseEntity<?> getFindByGroup(@PathVariable Long idGroup){
        try {
            TableKanbanResponse tableKanbanResponse = tableKanbanService.getFindByGroup(idGroup);
            return ResponseEntity.ok(tableKanbanResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar la tabla");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveTable(@RequestBody TableRequest tableRequest){
        try {
            TableKanbanResponse tableKanbanResponse = tableKanbanService.save(tableRequest);
            return ResponseEntity.ok(tableKanbanResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear la tabla"+ e.getMessage());
        }
    }

    @GetMapping("/columns/{idTable}")
    public ResponseEntity<?> getColumnsByTable(@PathVariable Long idTable){
        try {
            return ResponseEntity.ok(tableKanbanService.getColumnsByTable(idTable));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar las columnas de la tabla");
        }
    }
}
