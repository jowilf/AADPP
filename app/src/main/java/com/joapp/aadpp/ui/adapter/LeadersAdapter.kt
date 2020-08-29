package com.joapp.aadpp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joapp.aadpp.R
import com.joapp.aadpp.data.User
import com.joapp.aadpp.ui.fragment.LeadersFragment

class LeadersAdapter(
    var users: List<User> = listOf(),
    @LeadersFragment.LearningType val type: Int = LeadersFragment.FLAG_HOURS
) :
    RecyclerView.Adapter<LeadersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewId =
            if (type == LeadersFragment.FLAG_HOURS) R.layout.hours_item else R.layout.skill_item
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewId, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.name)
        val subtitle = itemView.findViewById<TextView>(R.id.subtitle)

        fun bind(user: User) {
            title.setText(user.name)
            if (type == LeadersFragment.FLAG_HOURS)
                subtitle.setText(
                    itemView.context.getString(
                        R.string.learning_hours,
                        user.hours,
                        user.country
                    )
                ) else subtitle.setText(
                itemView.context.getString(
                    R.string.learning_skill,
                    user.score,
                    user.country
                )
            )
        }
    }
}