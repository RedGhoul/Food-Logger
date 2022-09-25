import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home-component/home-component.component';
import { MainFoodEntryAppComponent } from './main-food-entry-app-component/main-food-entry-app.component';
import {MainFoodsAppComponent} from "./main-foods-app/main-foods-app.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'YourFoodEntries', component: MainFoodEntryAppComponent },
  { path: 'Foods', component: MainFoodsAppComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
