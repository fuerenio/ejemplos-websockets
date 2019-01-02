import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Message } from 'stompjs';

import { STOMPService } from '../../services/stomp';
import { ConfigService } from '../../services/config/config.service';

import { FormGroup, FormControl } from '@angular/forms';

/**
 * Url fuente: https://github.com/sjmf/ng2-stompjs-demo
 * https://github.com/devsullo/ng2-STOMP-Over-WebSocket/
 *
 */
@Component({
  selector: 'app-rawdata',
  templateUrl: './rawdata.component.html',
  styleUrls: ['./rawdata.component.css'],
  providers: [STOMPService]
})
export class RawDataComponent implements OnInit, OnDestroy {

  // Stream of messages
  public messages: Observable<Message>;

  // Array of historic message (bodies)
  public messageQueue: Array<string> = [];

  // A count of messages received
  public count = 0;

  private _counter = 1;

  formdata;

  /** Constructor */
  constructor(private _stompService: STOMPService,
    private _configService: ConfigService) { }

  ngOnInit() {
    // Get configuration from config service...
    this._configService.getConfig('api/config.json').then(
      config => {
        console.log(config);
        // ... then pass it to (and connect) STOMP:
        this._stompService.configure(config);
        this._stompService.try_connect().then(this.on_connect);
      }
    );

    this.formdata = new FormGroup({
         name: new FormControl(''),
         clase: new FormControl(''),
         pais: new FormControl('')
      });
  }

  ngOnDestroy() {
    this._stompService.disconnect();
  }

  public onClick() {
    const _getRandomInt = (min, max) => {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    };

    const mydata = { 'name': 'Test Name', 'type': 'Test Message',  'data' : [ _getRandomInt(1,100) ] };
    this._stompService.publishServer(mydata);

    this._counter++;
  }

  public syncStudent(data) {

    const studentData = { 'name': data.name, 'cls': data.clase,  'country' : data.pais, 'registerDate' : new Date().toISOString() };
    this._stompService.publishStudent(studentData);

    return false;
  }

  /** Callback on_connect to queue */
  public on_connect = () => {

    // Store local reference to Observable
    // for use with template ( | async )
    this.messages = this._stompService.messages;

    // Subscribe a function to be run on_next message
    this.messages.subscribe(this.on_next);
  }

  /** Consume a message from the _stompService */
  public on_next = (message: Message) => {

    // Store message in "historic messages" queue
    this.messageQueue.push(message.body + '\n');

    // Count it
    this.count++;

    // Log it to the console
    console.log(this.messages);
  }
}
