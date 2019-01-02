import { Component, OnInit, OnDestroy } from '@angular/core';
import { TestDaoService } from './services/test-dao.service';
import { RxStompService} from '@stomp/ng2-stompjs';
import { Subscription } from 'rxjs';
import { Message } from '@stomp/stompjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'indexeddb-test1';
  public receivedMessages: string[] = [];
  private topicSubscription: Subscription;

  constructor(private testDaoService: TestDaoService, private rxStompService: RxStompService) {

  }

  ngOnInit() {
    this.topicSubscription = this.rxStompService.watch('/topic/broadcastSubscribe').subscribe((message: Message) => {
      console.log('LLego un mensaje: ');
      console.log(message);
      console.log(message.body);
      // this.receivedMessages.push(message.body);
    });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }
}
