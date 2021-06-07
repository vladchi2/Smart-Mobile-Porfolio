//
//  ContentView.swift
//  Empathy2
//
//  Created by Mihail on 12/04/2021.
//

import SwiftUI

import FirebaseDatabase

struct ContentView: View {
    
    
    
    let users: [User] = [
        .init(id: 0,name: "Tim Coook", message: "My first message is a fuss and i missed my bus", imageName: "user4" ),
        .init(id: 1, name: "Cooking Tim", message: "And his second message is not better than first", imageName: "userr"),
        .init(id: 2, name: "Katy Party", message: "A little party never killed nobody beachA little party never killed nobody beachA little party never killed nobody beachA little party never killed nobody beachA little party never killed nobody beachA little party never killed nobody beach!", imageName: "5")
    ]
    
    

    var body: some View {
        
       
        NavigationView{
           
            List{
                ForEach(users, id: \.id) { user in
                   UserRow(user: user)
                }
                
            }.navigationTitle("Users")
            
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .preferredColorScheme(.dark)
    }
}
