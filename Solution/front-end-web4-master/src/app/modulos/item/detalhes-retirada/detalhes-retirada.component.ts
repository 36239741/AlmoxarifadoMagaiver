import { ItemRetiradaService } from 'src/app/shared/services/item-retirada.service';
import { ItemRetirada } from './../../../shared/model/item-retirada';
import { Component, OnInit, Input, ViewContainerRef, Inject } from '@angular/core';
import { ITdDataTableColumn } from '@covalent/core/data-table';
import { ActivatedRoute } from '@angular/router';
import { BehaviorTitleService } from 'src/app/shared/behavior/behavior-title.service';
import { TdDialogService } from '@covalent/core/dialogs';
import { MatDialogConfig, MAT_DIALOG_DATA } from '@angular/material/dialog';

const DECIMAL_FORMAT: (v: any) => any = (v: number) => new Intl.NumberFormat('pt-BR',{style: 'currency', currency:'BRL'} ).format(v);
@Component({
  selector: 'app-detalhes-retirada',
  templateUrl: './detalhes-retirada.component.html',
  styleUrls: ['./detalhes-retirada.component.css']
})
export class DetalhesRetiradaComponent implements OnInit {
  config: MatDialogConfig;
  columns:ITdDataTableColumn[] = [
    {name: 'codigo', label: 'Id'},
    {name: 'descricao', label: 'Descrição'},
    {name: 'quantidade', label: 'Quantidade'},
    {name: 'valor', label: 'Valor unitário', numeric: true, format: DECIMAL_FORMAT},
    {name: 'valorTotal', label: 'total', numeric: true, format: DECIMAL_FORMAT}
  ];
  data: ItemRetirada;
  date: String;
  dataTable: any = [];
  totalRetirada: number = 0;
  constructor(private activedRoute: ActivatedRoute,
              private behaviorTitle: BehaviorTitleService,
              private _dialogService: TdDialogService,
              private itemRetiradaService: ItemRetiradaService,
              @Inject(MAT_DIALOG_DATA) data) {
                this.data = data;
              }

  ngOnInit() {
    this.title();
    console.log(this.data);
    this.findItemRetirada(this.data['id']);
  }
  title() {
    this.behaviorTitle.setBehaviorTitle(this.activedRoute.snapshot.data['title']);
  }
  closeModal(){
    this._dialogService.closeAll();
  }
  findItemRetirada(itemRetiradaId: String){
    this.itemRetiradaService.findItemRetiradaId(itemRetiradaId).subscribe(data => {
      data.listItem.forEach(data => {
        data.valorTotal = 0;
        data.valorTotal = data.quantidade * data.valor;
        this.totalRetirada += data.valorTotal;
        console.log(this.totalRetirada);
      });
      this.dataTable = data.listItem;
      this.data = data;

    });
  }
}
