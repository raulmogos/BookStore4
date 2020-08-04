import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";

import {Observable} from "rxjs";
import { Client } from "../models/client.model";
import {Purchase} from "../models/purchase.model";
import {Book} from "../models/book.model";


@Injectable()
export class PurchaseService {
  private purchasesUrl = 'http://localhost:8080/api/purchases';
  private purchaseUrl = 'http://localhost:8080/api/purchase';

  constructor(private httpClient: HttpClient) {
  }

  getPurchases(): Observable<Purchase[]> {
    return this.httpClient.get<Array<Purchase>>(this.purchasesUrl);
  }

  getBooksForThisClient(client: Client): Observable<Book[]> {
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
    })
    return this.httpClient.put<Array<Book>>(`${this.purchasesUrl}/books-to-buy`, client, { headers });
  }

  addNewPurchase(purchase: Purchase) {
    console.log(purchase)
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
    })
    this.httpClient.post<Client>(this.purchaseUrl, purchase, { headers }).subscribe();
  }

  updatePurchase(purchase: Purchase): void {
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
    })
    this.httpClient.put<Client>(this.purchaseUrl, purchase, { headers }).subscribe();
  }

  deleteClient(id: number) {
    this.httpClient.delete<Client>(`${this.purchaseUrl}/${id}`).subscribe();
  }
}
