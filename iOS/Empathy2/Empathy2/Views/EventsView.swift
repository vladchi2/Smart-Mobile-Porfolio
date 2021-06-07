//
//  EventsView.swift
//  Empathy2
//
//  Created by Vlad on 15/04/2021.
//

import SwiftUI
import Firebase
import UIKit


struct EventsView: View {
    
    let db1 = Firestore.firestore()
    
    let events: [Event] = [
        .init(id:0,time: "4 hrs ago", body: "Hei there people, I will have a little gathering to share our stories about whatever happend to us at some point. Who is interested you are very welcome to join ", user: "Billy Boy", personNumber: 10, dateTime: Date(), topic: "Gathering",location: "Gravesandestraat 20, Eindhoven, The Netherlands"),
        .init(id:1,time: "3 hrs ago", body: "Hei there people, I will have a little gathering to share our stories about whatever happend to us at some point. Who is interested you are very welcome to join ", user: "Billy Boy", personNumber: 10, dateTime: Date(), topic: "Gathering",location: "Gravesandestraat 20, Eindhoven, The Netherlands"),
        .init(id:2,time: "18 minutes ago", body: "Hei there people, I will have a little gathering to share our stories about whatever happend to us at some point. Who is interested you are very welcome to join ", user: "Billy Boy", personNumber: 10, dateTime: Date(), topic: "Gathering",location: "Gravesandestraat 20, Eindhoven, The Netherlands"),
        .init(id:3,time: "yesterday", body: "Hei there people, I will have a little gathering to share our stories about whatever happend to us at some point. Who is interested you are very welcome to join ", user: "Billy Boy", personNumber: 10, dateTime: Date(), topic: "Gathering",location: "Gravesandestraat 20, Eindhoven, The Netherlands"),
        ]
    
    var body: some View {
        NavigationView{
          
            
            List{
                WeatherButton(title: "Post an event", textColor: .white, backgroundColor: .blue)
                    
                
                //------Posts---------
                ForEach(events, id: \.id) { event in
                    EventRow(event: event)
                }
            }.navigationTitle("Events")
            
        }
    }
}



struct EventsView_Previews: PreviewProvider {
    static var previews: some View {
        EventsView()
    }
}
