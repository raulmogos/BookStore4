import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from "@angular/common/http";

import {Observable} from "rxjs";
import { Client } from "../models/client.model";
import {Book} from "../models/book.model";


@Injectable()
export class ClientService {
  private clientsUrl = 'http://localhost:8080/api/clients';
  private clientUrl = 'http://localhost:8080/api/client';

  constructor(private httpClient: HttpClient) {
  }

  getClients(): Observable<Client[]> {
    return this.httpClient.get<Array<Client>>(this.clientsUrl);
  }

  getClientsFromIds(ids: number[]): Observable<Client[]> {
    return this.httpClient.put<Array<Client>>(`${this.clientsUrl}/ids`, ids);
  }

  getOneClient(id): Observable<Client> {
    return this.httpClient.get<Client>(`${this.clientUrl}/${id}`);
  }

  addNewClient(client: Client) {
    console.log(client)
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
    })
    this.httpClient.post<Client>(this.clientUrl, client, { headers }).subscribe();

  }

  updateClient(client: Client): void {
    const headers = new HttpHeaders({
      'Content-Type':  'application/json',
    })
    this.httpClient.put<Client>(this.clientUrl, client, { headers }).subscribe();
  }

  deleteClient(id: number) {
    this.httpClient.delete<Client>(`${this.clientUrl}/${id}`).subscribe();
  }
}
