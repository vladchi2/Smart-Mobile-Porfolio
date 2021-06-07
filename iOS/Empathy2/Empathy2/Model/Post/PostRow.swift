//
//  PostRow.swift
//  Empathy2
//
//  Created by Mihail on 12/04/2021.
//

import Foundation
import SwiftUI

struct PostRow:View {
    
    let post: Post
    
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
                    Text(post.user).font(.headline)
                    Text("Posted " + post.time).font(.subheadline)
                }
                
            }.padding(.leading,16).padding(.top,16)
            
            Text(post.body)
                .padding(.leading,16)
            Image("baby").resizable().aspectRatio(contentMode: .fill).clipped()
        }
        
    }
}
