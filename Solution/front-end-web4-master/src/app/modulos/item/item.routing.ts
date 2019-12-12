import { Routes, RouterModule } from '@angular/router';
import { ScreenComponent } from './screen/screen.component';
import { ItemRetiradaComponent } from './item-retirada/item-retirada.component';
import { NgModule } from '@angular/core';
import { DetalhesRetiradaComponent } from './detalhes-retirada/detalhes-retirada.component';
import { RetirarItemComponent } from './retirar-item/retirar-item.component';
import { ConfirmarRetiradaComponent } from './confirmar-retirada/confirmar-retirada.component';
import { ConcluirRetiradaComponent } from './concluir-retirada/concluir-retirada.component';
import { ItemResolverService } from '../../shared/resolver/item-resolver.service';


const routes: Routes = [
  { path: '' , component: ScreenComponent, data: {title: 'Retirada de itens'}, children: [
    { path: '' , component: ItemRetiradaComponent},
    {path: 'detalhes-retirada', component: DetalhesRetiradaComponent, data: {title: 'Detalhes da retirada de itens'}},
    {path: 'retirar-item', component: RetirarItemComponent, data: {title: 'Retirar item'}, resolve: {findAll: ItemResolverService}},
    {path: 'confirmar-retirada', component: ConfirmarRetiradaComponent, data: {title: 'Confirmar retirada'}},
    {path: 'concluir-retirada', component: ConcluirRetiradaComponent, data: {title: 'Concluir retirada'}}
  ]}
  ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItensRoutingModule { }
