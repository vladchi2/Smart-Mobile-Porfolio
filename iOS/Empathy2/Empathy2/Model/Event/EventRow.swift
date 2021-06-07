//
//  EventRow.swift
//  Empathy2
//
//  Created by Vlad on 15/04/2021.
//

import SwiftUI

struct EventRow: View {
    let event: Event
    
    var body: some View{
        VStack(alignment: .leading)
        {
            HStack
            {
                Image("user1")
                    .resizable()
                    .clipShape(/*@START_MENU_TOKEN@*/Circle()/*@END_MENU_TOKEN@*/)
                    .frame(width: 60, height: 60)
                    .clipped()
                
                VStack(alignment: .leading){
                    Text(event.user).font(.headline)
                    Text("Posted " + event.time).font(.subheadline)
                }
                
            }.padding(.leading,16).padding(.top,16)
            
            Text(event.body)
                .padding(.leading,16)
            Spacer()
            Text("Maximum number of  participants: \(event.personNumber)")
                .padding(.leading,16)
            Spacer()
            Text("Date and time: \(event.dateTime)")
                .padding(.leading,16)
            Spacer()
            Text("Topic of the event: \(event.topic)")
                .padding(.leading,16)
            Spacer()
            Text(event.location)
                .padding(.leading,16)
        }
        
    }
}
