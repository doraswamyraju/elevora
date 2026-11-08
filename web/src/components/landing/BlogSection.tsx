import React from 'react';
import { BookOpen, ArrowRight, Clock, User } from 'lucide-react';

export const BlogSection: React.FC = () => {
  const articles = [
    {
      title: "5 Diplomatic Phrases to Disagree with Senior Leaders Respectfully",
      category: "Managerial Skills",
      readTime: "4 min read",
      author: "Priya Sharma",
      date: "Nov 02, 2026",
      excerpt: "Learn how to push back against unrealistic deadlines or scope creep without sounding confrontational or passive."
    },
    {
      title: "The BLUF Technique: How to Write High-Impact Executive Emails",
      category: "Workplace Writing",
      readTime: "5 min read",
      author: "Doraswamy Raju",
      date: "Oct 28, 2026",
      excerpt: "Why senior executives love 'Bottom Line Up Front' and how structuring your updates this way gets decisions made twice as fast."
    },
    {
      title: "Overcoming Meeting Anxiety: How to Speak Up in Large Cross-Team Syncs",
      category: "Speaking Confidence",
      readTime: "6 min read",
      author: "Elevora Academic Team",
      date: "Oct 20, 2026",
      excerpt: "Practical mental models and pre-meeting rehearsals with AI speaking tutors to eliminate speech paralysis and imposter feelings."
    }
  ];

  return (
    <section id="blog" className="py-20 md:py-32 relative bg-[#080C16]">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {/* Header */}
        <div className="flex flex-col md:flex-row md:items-end justify-between mb-16 gap-6">
          <div>
            <div className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-amber-500/10 border border-amber-500/25 text-amber-300 text-xs font-semibold uppercase tracking-wider mb-3">
              <BookOpen className="w-3.5 h-3.5" />
              <span>Insights & Tips</span>
            </div>
            <h2 className="text-3xl sm:text-4xl font-extrabold text-white font-display">
              From the <span className="text-amber-400">Elevora Blog</span>
            </h2>
            <p className="mt-2 text-base text-slate-300">
              Free strategies, corporate vocabulary cheat-sheets, and executive communication guides.
            </p>
          </div>

          <a 
            href="#blog" 
            className="inline-flex items-center gap-2 text-sm font-bold text-amber-400 hover:text-amber-300 group"
          >
            <span>View all articles</span>
            <ArrowRight className="w-4 h-4 group-hover:translate-x-1 transition-transform" />
          </a>
        </div>

        {/* Articles Grid */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {articles.map((art, idx) => (
            <article 
              key={idx}
              className="bg-[#0E172B] border border-slate-800 rounded-2xl p-6 hover:border-amber-500/40 transition-all hover:-translate-y-1 flex flex-col justify-between group cursor-pointer"
            >
              <div>
                <div className="flex items-center justify-between text-xs text-slate-400 mb-3">
                  <span className="text-amber-400 font-semibold px-2 py-0.5 rounded-md bg-amber-500/10 border border-amber-500/20">
                    {art.category}
                  </span>
                  <span className="flex items-center gap-1">
                    <Clock className="w-3 h-3" />
                    {art.readTime}
                  </span>
                </div>

                <h3 className="text-lg font-bold text-white group-hover:text-amber-400 transition-colors leading-snug mb-3">
                  {art.title}
                </h3>

                <p className="text-xs text-slate-400 leading-relaxed">
                  {art.excerpt}
                </p>
              </div>

              <div className="mt-6 pt-4 border-t border-slate-800 flex items-center justify-between text-xs text-slate-400">
                <span className="flex items-center gap-1.5 font-medium text-slate-300">
                  <User className="w-3.5 h-3.5 text-amber-400" />
                  {art.author}
                </span>
                <span>{art.date}</span>
              </div>
            </article>
          ))}
        </div>

      </div>
    </section>
  );
};
