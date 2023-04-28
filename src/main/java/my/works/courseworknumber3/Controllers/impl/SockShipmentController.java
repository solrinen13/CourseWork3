package my.works.courseworknumber3.Controllers.impl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.works.courseworknumber3.Model.Color;
import my.works.courseworknumber3.Model.SockShipment;
import my.works.courseworknumber3.Model.SockSize;
import my.works.courseworknumber3.Services.SocksAccountingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name =  "Склад носков", description = "CRUD для работы с партиями носков")
public class SockShipmentController {
    private final SocksAccountingService socksAccountingService;
    public SockShipmentController (SocksAccountingService socksAccountingService){
        this.socksAccountingService = socksAccountingService;
    }

    @PostMapping
    @ResponseBody
    @Operation(
            summary = "Создание партии носков"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация загружена успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SockShipment.class))
                            )
                    }
            )
    })
    public ResponseEntity<Void> createSockShipment (@RequestBody SockShipment newShipment){
        try{
        socksAccountingService.addSocks(newShipment);
        return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            e.printStackTrace();
           return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }

    @GetMapping
    @ResponseBody
    @Operation(
            summary = "Создание партии носков"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация получена успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SockShipment.class))
                            )
                    }
            )
    })
    public ResponseEntity<Integer> getSockShipment (@RequestParam(required = false) Color color, @RequestParam(required = false) SockSize size,
                                                    @RequestParam(required = false) Integer cottonMin, @RequestParam(required = false) Integer cottonMax){

        try{
           Integer integer =   socksAccountingService.getSocksQuantity(color, size, cottonMin,  cottonMax);
            return new ResponseEntity<>(integer, HttpStatus.OK);
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }

    @PutMapping
    @ResponseBody
    @Operation(
            summary = "Отгрузка носков из партий"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация изменена успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SockShipment.class))
                            )
                    }
            )
    })
    public ResponseEntity<Void> updateSockShipment (@RequestBody SockShipment newShipment){
        try{
            socksAccountingService.putSocks(newShipment);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }

    @DeleteMapping
    @ResponseBody
    @Operation(
            summary = "Отгрузка бракованных носков из партий"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация изменена успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SockShipment.class))
                            )
                    }
            )
    })
    public ResponseEntity<Void> deleteSockShipment (@RequestBody SockShipment sockShipment){
        try{
            socksAccountingService.deleteSocks(sockShipment);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();}
    }
}
