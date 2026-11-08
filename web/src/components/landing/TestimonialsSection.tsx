import React from 'react';
import { Star, Quote, Award } from 'lucide-react';

export const TestimonialsSection: React.FC = () => {
  const testimonials = [
    {
      name: "Rohit Verma",
      role: "Engineering Lead",
      company: "Fintech Solutions",
      quote: "Before Elevora, I used to hesitate during global sprint demos with US clients. After completing Module 1 and practicing daily with the AI tutor, I speak assertively and lead calls effortlessly.",
      rating: 5,
      avatarInitials: "RV"
    },
    {
      name: "Ananya Nair",
      role: "Operations Manager",
      company: "OmniLogistics India",
      quote: "The situational role-play is a game changer! Being able to rehearse tough conversations before talking to senior stakeholders gave me tremendous confidence. Worth every rupee.",
      rating: 5,
      avatarInitials: "AN"
    },
    {
      name: "Siddharth Rao",
      role: "Product Manager",
      company: "CloudScale SaaS",
      quote: "Most English courses teach grammar rules that don't apply to corporate meetings. Elevora gave me real executive vocabulary and phrasing that immediately improved my stakeholder presentations.",
      rating: 5,
      avatarInitials: "SR"
    }
  ];

  return (
    <section id="testimonials" className="py-20 md:py-32 relative bg-[#0B1220] border-t border-slate-800">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {/* Header */}
        <div className="text-center max-w-2xl mx-auto mb-16">
          <div className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-amber-500/10 border border-amber-500/25 text-amber-300 text-xs font-semibold uppercase tracking-wider mb-3">
            <Award className="w-3.5 h-3.5" />
            <span>Learner Success Stories</span>
          </div>
          <h2 className="text-3xl sm:text-4xl font-extrabold text-white font-display">
            Trusted by <span className="text-amber-400">Professionals & Leaders</span>
          </h2>
          <p className="mt-3 text-base text-slate-300">
            See how real professionals transformed their workplace communication skills with Elevora.
          </p>
        </div>

        {/* Testimonials Grid */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {testimonials.map((t, idx) => (
            <div
              key={idx}
              className="bg-[#0E172B] border border-slate-800 rounded-2xl p-7 flex flex-col justify-between hover:border-amber-500/40 transition-all hover:-translate-y-1 relative"
            >
              <div>
                {/* 5-star rating */}
                <div className="flex items-center gap-1 text-amber-400 mb-4">
                  {[...Array(t.rating)].map((_, i) => (
                    <Star key={i} className="w-4 h-4 fill-amber-400 text-amber-400" />
                  ))}
                </div>

                <Quote className="w-8 h-8 text-slate-700 mb-2" />

                <p className="text-sm text-slate-300 leading-relaxed italic">
                  "{t.quote}"
                </p>
              </div>

              {/* Author info */}
              <div className="mt-6 pt-5 border-t border-slate-800 flex items-center gap-3">
                <div className="w-10 h-10 rounded-full bg-gradient-to-tr from-amber-500 to-yellow-300 p-0.5 shrink-0">
                  <div className="w-full h-full rounded-full bg-slate-950 flex items-center justify-center font-bold text-amber-400 text-xs">
                    {t.avatarInitials}
                  </div>
                </div>
                <div>
                  <h4 className="text-sm font-bold text-white">{t.name}</h4>
                  <p className="text-xs text-slate-400">{t.role} • {t.company}</p>
                </div>
              </div>
            </div>
          ))}
        </div>

      </div>
    </section>
  );
};
