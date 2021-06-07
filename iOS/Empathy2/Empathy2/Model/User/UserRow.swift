//
//  UserRow.swift
//  Empathy2
//
//  Created by Mihail on 12/04/2021.
//

import Foundation
import SwiftUI

struct UserRow:View {
    let user : User
    var body: some View{
        HStack{
            Image(user.imageName)
                .resizable()
                .clipShape(Circle())
                .overlay(Circle().stroke(Color.white, lineWidth: 2))
                .frame(width: 70, height: 70)
                .clipped()
                
                
            VStack(alignment: .leading) {
                Text(user.name).font(.headline)
                Text(user.message).font(.subheadline)
            }.padding((8))
        }
    }
}
