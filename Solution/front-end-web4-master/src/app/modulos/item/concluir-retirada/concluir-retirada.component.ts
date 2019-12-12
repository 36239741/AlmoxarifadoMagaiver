import { ItemRetiradaService } from 'src/app/shared/services/item-retirada.service';
import { ItemRetirada } from './../../../shared/model/item-retirada';
import { Component, OnInit } from '@angular/core';
import { ITdDataTableColumn } from '@covalent/core/data-table';
import { BehaviorItemRetirada } from 'src/app/shared/services/behavior/behavior-item-retirada.ts.service';
import { MessageService } from 'src/app/shared/services/message.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-concluir-retirada',
  templateUrl: './concluir-retirada.component.html',
  styleUrls: ['./concluir-retirada.component.css']
})
export class ConcluirRetiradaComponent implements OnInit {

  constructor(private behaviorItemRetirada: BehaviorItemRetirada,
              private itemRetiradaSerice: ItemRetiradaService,
              private message: MessageService,
              private router: Router) { }
  itemRetirada: ItemRetirada;
  date: Date = new Date();
  localDate: String;
  columns: ITdDataTableColumn[] = [
    {name: 'codigo', label: 'Codigo'},
    {name: 'descricao', label: 'Descrição'},
    {name: 'quantidade', label: 'Quantidade'},
  ];
  data: any[] = [];
  ngOnInit() {
    this.recuperarObjeto();
  }
  recuperarObjeto(){
   this.itemRetirada =  this.behaviorItemRetirada.itemRetiradaGet().value;
   this.data =  this.itemRetirada.listItem;
   this.localDate = this.date.getDay() + '-' + this.date.getMonth() + '-' + this.date.getFullYear();
  }
  save(){
    this.itemRetiradaSerice.saveItemRetirada(this.itemRetirada).subscribe(() => {
      this.message.toastSuccess('Retirado com sucesso.');
      this.router.navigate(['/item-retirada']);

    },
    error =>{
      this.message.toastError(error.error.message);
    });
  }
}
