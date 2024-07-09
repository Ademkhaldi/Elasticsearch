import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DataAum } from '../DataAum.model';

@Injectable({
  providedIn: 'root'
})
export class DataAumService {

  apiUrl = 'http://localhost:8097/api/data_aum'; // Replace with your Spring Boot API URL

  constructor(private http: HttpClient) { }

  findAll(): Observable<DataAum[]> {
    return this.http.get<DataAum[]>(this.apiUrl+"/Data");
  }

  findById(id: String): Observable<DataAum> {
    return this.http.get<DataAum>(`${this.apiUrl}/${id}`);
  }

  search(id?: string, date?: string, assetType?: string, client?: string, value?: string): Observable<DataAum[]> {
    let params = new HttpParams();
    if (id) {
      params = params.append('id', id);
    }
    if (date) {
      params = params.append('date', date);
    }
    if (assetType) {
      params = params.append('assetType', assetType);
    }
    if (client) {
      params = params.append('client', client);
    }
    if (value) {
      params = params.append('value', value);
    }
    return this.http.get<DataAum[]>(this.apiUrl+"/search", { params });
  }

}
