import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path: '' ,  loadChildren: () => import(`./modulos/home/home.module`).then(m => m.HomeModule)},
  { path: 'item-retirada', loadChildren: () => import(`./modulos/item/itens.module`).then(m => m.ItensModule)},
  { path: 'home', loadChildren: () => import(`./modulos/home/home.module`).then(m => m.HomeModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
