import { Item } from './../../../shared/model/item';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorTitleService } from 'src/app/shared/behavior/behavior-title.service';
import { ITdDataTableColumn } from '@covalent/core/data-table';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorItemService } from 'src/app/shared/services/behavior/behavior-item.service';
import { ItemRetirada } from 'src/app/shared/model/item-retirada';
import { BehaviorItemRetirada } from 'src/app/shared/services/behavior/behavior-item-retirada.ts.service';
import { MessageService } from 'src/app/shared/services/message.service';


@Component({
  selector: 'app-confirmar-retirada',
  templateUrl: './confirmar-retirada.component.html',
  styleUrls: ['./confirmar-retirada.component.css']
})
export class ConfirmarRetiradaComponent implements OnInit {
  form: FormGroup;
  itemRetirada: ItemRetirada = new ItemRetirada();
  columns: ITdDataTableColumn[] = [
    {name: 'codigo', label: 'Id'},
    {name: 'descricao', label: 'Descrição'},
    {name: 'quantidade', label: 'Quantidade'},
    {name: 'cancelar', label: 'Remover'},
  ];
  data: any[] = [];
  constructor(private activedRoute: ActivatedRoute,
              private behaviorTitle: BehaviorTitleService,
              private formBuilder: FormBuilder,
              private behaviorItem: BehaviorItemService,
              private router: Router,
              private message: MessageService,
              private behaviorItemRetirada: BehaviorItemRetirada
             ) { }

  ngOnInit() {
    this.title();
    this.reactForms();
    this.data = this.behaviorItem.itemGet().value;
  }
  reactForms(){
      this.form = this.formBuilder.group({
      quemRetirou: ['', Validators.required],
      localRetirada: ['', Validators.required]
    });
  }
  recuperarListaRetirada(){
    if(this.form.valid){
      this.itemRetirada.listItem =  this.behaviorItem.itemGet().value;
      this.itemRetirada.localRetirada = this.form.get('localRetirada').value;
      this.itemRetirada.quemRetirou = this.form.get('quemRetirou').value;
      this.behaviorItemRetirada.itemRetiradaSet(this.itemRetirada);
      this.router.navigate(['/item-retirada/concluir-retirada'])
    }
  }
  verificarItem(){
    if(this.data.length > 0){
      this.recuperarListaRetirada();
    }
    else {
      this.message.toastError('Nao exite nenhum item na lista para retirada');
    }
  }
  removeList(item: Item){
    console.log(item);
    let itens: any[] = this.behaviorItem.itemGet().value;
    var index = itens.indexOf(item);
    itens.splice(index, 1);
    this.data = itens;
  }
  title() {
    this.behaviorTitle.setBehaviorTitle(this.activedRoute.snapshot.data['title']);
  }
}
