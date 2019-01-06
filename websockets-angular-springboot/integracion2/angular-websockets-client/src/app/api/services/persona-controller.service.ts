/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { PersonaDto } from '../models/persona-dto';

/**
 * Persona Controller
 */
@Injectable({
  providedIn: 'root',
})
class PersonaControllerService extends __BaseService {
  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param Authorization notaspro token
   * @return OK
   */
  getAllUsingGETResponse(Authorization?: string): __Observable<__StrictHttpResponse<Array<PersonaDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (Authorization != null) __headers = __headers.set('Authorization', Authorization.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/persona/getAll`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<PersonaDto>>;
      })
    );
  }
  /**
   * @param Authorization notaspro token
   * @return OK
   */
  getAllUsingGET(Authorization?: string): __Observable<Array<PersonaDto>> {
    return this.getAllUsingGETResponse(Authorization).pipe(
      __map(_r => _r.body as Array<PersonaDto>)
    );
  }

  /**
   * @param params The `PersonaControllerService.SaveUsingPOSTParams` containing the following parameters:
   *
   * - `personaDto`: personaDto
   *
   * - `Authorization`: notaspro token
   *
   * @return OK
   */
  saveUsingPOSTResponse(params: PersonaControllerService.SaveUsingPOSTParams): __Observable<__StrictHttpResponse<PersonaDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.personaDto;
    if (params.Authorization != null) __headers = __headers.set('Authorization', params.Authorization.toString());
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/persona/save`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<PersonaDto>;
      })
    );
  }
  /**
   * @param params The `PersonaControllerService.SaveUsingPOSTParams` containing the following parameters:
   *
   * - `personaDto`: personaDto
   *
   * - `Authorization`: notaspro token
   *
   * @return OK
   */
  saveUsingPOST(params: PersonaControllerService.SaveUsingPOSTParams): __Observable<PersonaDto> {
    return this.saveUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as PersonaDto)
    );
  }
}

module PersonaControllerService {

  /**
   * Parameters for saveUsingPOST
   */
  export interface SaveUsingPOSTParams {

    /**
     * personaDto
     */
    personaDto: PersonaDto;

    /**
     * notaspro token
     */
    Authorization?: string;
  }
}

export { PersonaControllerService }
