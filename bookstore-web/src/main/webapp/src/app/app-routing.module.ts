import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BooksComponent} from "./books/books.component";
import {ClientsComponent} from "./clients/clients.component";
import {BookComponent} from "./books/book/book.component";
import {AddNewBookComponent} from "./books/add-new-book/add-new-book.component";
import {ClientComponent} from "./clients/client/client.component";
import {AddNewClientComponent} from "./clients/add-new-client/add-new-client.component";
import {BuyBookComponent} from "./clients/buy-book/buy-book.component";
import {PurchasesComponent} from "./purchases/purchases.component";


const routes: Routes = [
  // {path: 'students', component: StudentsComponent},
  // {path: 'student/detail/:id', component: StudentDetailComponent},
  // {path: 'student/new', component: StudentNewComponent},
  // {path: 'disciplines', component: DisciplinesComponent},
  {path: 'book/new', component: AddNewBookComponent},
  {path: 'books', component: BooksComponent},
  {path: 'book/:id', component: BookComponent},

  {path: 'client/new', component: AddNewClientComponent},
  {path: 'clients', component: ClientsComponent},
  {path: 'client/:id', component: ClientComponent},
  {path: 'client/buy-book/:id', component: BuyBookComponent},

  {path: 'purchases', component: PurchasesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
