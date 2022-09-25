import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home-component/home-component.component';
import { MainFoodEntryAppComponent } from './main-food-entry-app-component/main-food-entry-app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FoodEntryComponent } from './food-entry-form-component/food-entry-form.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateFoodEntryModalComponent } from './create-entry-modal-component/create-entry-modal.component';
import { CreateEditFoodModalComponent } from './create-edit-food-modal-component/create-edit-food-modal.component';

import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { MainFoodsAppComponent } from './main-foods-app/main-foods-app.component';
import {FoodEntryTableComponent} from "./food-entry-table-component/food-entry-table-component.component";
import {AlertModule} from "ngx-bootstrap/alert";
import {PaginationModule} from "ngx-bootstrap/pagination";
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FoodEntryComponent,
    FoodEntryTableComponent,
    MainFoodEntryAppComponent,
    CreateFoodEntryModalComponent,
    CreateEditFoodModalComponent,
    MainFoodsAppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    TypeaheadModule.forRoot(),
    ModalModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    AlertModule,
    PaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
