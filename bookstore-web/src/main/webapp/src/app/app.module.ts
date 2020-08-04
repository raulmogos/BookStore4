import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BookListComponent } from './books/book-list/book-list.component';
import { BooksComponent } from './books/books.component';
import {BookService} from "./shared/services/book.service";
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import { ClientsComponent } from './clients/clients.component';
import { BookComponent } from './books/book/book.component';
import {FormsModule} from "@angular/forms";
import { AddNewBookComponent } from './books/add-new-book/add-new-book.component';
import {ClientService} from "./shared/services/client.service";
import { ClientListComponent } from './clients/client-list/client-list.component';
import { AddNewClientComponent } from './clients/add-new-client/add-new-client.component';
import {ClientComponent} from "./clients/client/client.component";
import { BuyBookComponent } from './clients/buy-book/buy-book.component';
import { PurchasesComponent } from './purchases/purchases.component';
import {PurchaseService} from "./shared/services/purchase.service";

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    BooksComponent,
    ClientsComponent,
    BookComponent,
    AddNewBookComponent,
    ClientListComponent,
    ClientComponent,
    AddNewClientComponent,
    BuyBookComponent,
    PurchasesComponent
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule
    ],
  providers: [BookService, ClientService, PurchaseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
